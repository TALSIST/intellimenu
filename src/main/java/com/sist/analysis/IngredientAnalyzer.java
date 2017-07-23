package com.sist.analysis;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sist.recipe.IngredientMapper;

@Component
@Scope("prototype")
public class IngredientAnalyzer {
	private List<String> ingredient;
	// @Autowired
	private IngredientMapper ingrMapper;

	public IngredientAnalyzer() {
		updateIngredient();
	}

	// TODO : 재료 추가/제거 발생하면 이 Method를 호출
	public void updateIngredient() {
		ingredient = ingrMapper.selectWholeIngredient();
	}

	public void execute() {
		   SparkConf conf=new SparkConf().setAppName("daum").setMaster("local");
		   JavaSparkContext sc=new JavaSparkContext(conf);
		   
		   JavaRDD<String> files=sc.textFile("./input/daum.txt");
		   
		   List<String> list=RankData.daumRank();
		   final Pattern[] p=new Pattern[list.size()];
		   for(int i=0;i<p.length;i++)
		   {
			   p[i]=Pattern.compile(list.get(i));
		   }
		   
		   final List<String> wordList=new ArrayList<String>();
		   JavaRDD<String> words=files.flatMap(new FlatMapFunction<String, String>() {
			
			@Override
			public Iterable<String> call(String s) throws Exception {
				// TODO Auto-generated method stub
				System.out.println(s);
				Matcher[] m=new Matcher[p.length];
				// ((211).(238).(142).(98))
				for(int i=0;i<m.length;i++)
				{
					m[i]=p[i].matcher(s);
					while(m[i].find())
					{
						System.out.println("find:"+m[i].group());
						wordList.add(m[i].group());
					}
				}
				return wordList;
			}
		  });
		  JavaPairRDD<String, Integer> counts=
				   words.mapToPair(new PairFunction<String,String, Integer>() {

					@Override
				   public Tuple2<String, Integer> call(String s) throws Exception {
								// TODO Auto-generated method stub
								return new Tuple2<String, Integer>(s, 1);
					}
		  });
		  //=========================================Mapper
		  JavaPairRDD<String, Integer> res=counts.reduceByKey(new Function2<Integer, Integer, Integer>() {
			
			@Override
			public Integer call(Integer sum, Integer i) throws Exception {
				// TODO Auto-generated method stub
				return sum+i;
			}
		});
		
		res.saveAsTextFile("./output_daum/");
	   }
}}

}
