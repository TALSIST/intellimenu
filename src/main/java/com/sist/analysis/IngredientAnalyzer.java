package com.sist.analysis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sist.recipe.IngredientMapper;

import scala.Tuple2;

public class IngredientAnalyzer implements Serializable {
	private IngredientMapper ingrMapper;
	private List<String> ingredient;
	private SparkConf conf;
	private JavaSparkContext sc;

	public IngredientAnalyzer() {
		conf = new SparkConf().setAppName("ingredient").setMaster("local[2]");
		sc = new JavaSparkContext(conf);
	}

	// 재료 추가/제거 발생하면 이 Method를 호출
	public void updateIngredient() {
		ingredient = ingrMapper.selectWholeIngredient();
	}

	public void execute(String title) {
		if (ingredient == null) { updateIngredient(); }
		JavaRDD<String> files = sc.textFile("./ingredient.txt");
		final List<String> list = new ArrayList<>();
		for (String str : ingredient) { list.add(new String(str)); }
		final Pattern[] p = new Pattern[list.size()];
		for (int i = 0; i < p.length; i++) {
			p[i] = Pattern.compile(list.get(i));
		}

		final List<String> wordList = new ArrayList<String>();
		JavaRDD<String> words = files.flatMap(new FlatMapFunction<String, String>() {
			@Override
			public Iterable<String> call(String s) throws Exception {
				System.out.println(s);
				Matcher[] m = new Matcher[p.length];
				for (int i = 0; i < m.length; i++) {
					m[i] = p[i].matcher(s);
					while (m[i].find()) {
						wordList.add(m[i].group());
					}
				}
				return wordList;
			}
		});
		JavaPairRDD<String, Integer> counts = words.mapToPair(new PairFunction<String, String, Integer>() {
			@Override
			public Tuple2<String, Integer> call(String s) throws Exception {
				return new Tuple2<String, Integer>(s, 1);
			}
		});
		JavaPairRDD<String, Integer> res = counts.reduceByKey(new Function2<Integer, Integer, Integer>() {
			@Override
			public Integer call(Integer sum, Integer i) throws Exception {
				return sum + i;
			}
		});

		res.saveAsTextFile("./output/");
	}
}
