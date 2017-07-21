package com.sist.spark;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;

import com.sist.collertor.MongoMartVO;

import scala.Tuple2;

public class sparkRank {

	public void sparkRun(String fileName,String type,Configuration conf){
		String data="";
		String[] fish={"emart_fish","homplus_fish.txt","lotte_fish"};
		String[] veig={"emart_vegi","homplus_vegi.txt","lotte_vegi"};
		String[] dump=null;
		
		try{
		if(type.equals("fish")){
			dump=fish;
		}else if(type.equals("veig")){
			dump=veig;
			
		}
		
	
		FileSystem fs=FileSystem.get(conf);
		SparkConf sconf=new SparkConf().setAppName("food").setMaster("local");
		JavaSparkContext sc=new JavaSparkContext(sconf);
		String defalutPath="/food_data/";
		for (int i = 0; i < dump.length; i++) {
			
			FSDataInputStream is=fs.open(new Path(defalutPath+dump[i]));
			BufferedReader br=new BufferedReader(new InputStreamReader(is, "utf-8"));
			while(true){
				String line=br.readLine();
				if(line==null)break;
				//System.out.println(line);
				
				data+=line+"\n";
			}
			
			data=data.substring(0,data.lastIndexOf("\n"));
			br.close();
			
			
		}
			
			System.out.println(data);
			
			/*********************/
			JavaRDD<String> words=sc.textFile("./data/"+fileName);
			JavaPairRDD<String, Integer> counts=words.mapToPair(new PairFunction<String,String,Integer>(){
				
				@Override
				public Tuple2<String, Integer> call(String s) throws Exception {
					// TODO Auto-generated method stub
					return new Tuple2<String,Integer>(s,1);
				}
												//sum	=	sum		+		i
			});
					
			JavaPairRDD<String,Integer>	res	=counts.reduceByKey(new Function2<Integer, Integer, Integer>() {
				//sum=sum+i
				@Override
				public Integer call(Integer x, Integer y) throws Exception {
					// 					sum					i
					return x+y;
				}
				
			});
			//res.join(res);
			res.saveAsTextFile("./food_data/"+type);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	public static List<MongoMartVO> poodfileReader(){
		String line="";
		List<MongoMartVO> list=new ArrayList<MongoMartVO>();
		try {
			
			FileReader fr=new FileReader("./food_data/Emart/part-00000");
			BufferedReader br=new BufferedReader(fr);
			while (true) {
				line=br.readLine();
				
				if(line==null){
					break;
				}
				line=line.replace("(", "");
				line=line.replace(")", "");
				String[] data=line.split(",");
				MongoMartVO vo=new MongoMartVO();
				vo.setItem(data[0]);
				vo.setHit(Integer.parseInt(data[1]));
				
				list.add(vo);
				//System.out.println(vo.getDate()+":  "+vo.getCount()+":  "+vo.getFoodName());
				
			}
		} catch (Exception e) {
		  System.out.println(e.getMessage());
		}
		return list;
	}

}
