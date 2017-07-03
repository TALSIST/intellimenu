package com.sist.recipe;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.IngredientVO;
import com.sist.vo.ReligionVO;
import com.sist.vo.VegeterianVO;

public interface IngredientMapper {

	@Select("SELECT COUNT(*) FROM ingredient")
	public int selectIngrTotal();
	
	@Results(value= {
			@Result(property="id", column="id"),
			@Result(property="name", column="name"),
			@Result(property="cal", column="cal"),
			@Result(property="season", column="id", javaType=List.class, 
				many=@Many(select="selectIngrSeason")),
			@Result(property="religion", column="id", javaType=List.class, 
				many=@Many(select="selectIngrReligion")),
			@Result(property="vegeterian", column="id", javaType=List.class, 
				many=@Many(select="selectIngrVegeterian"))
	})
	@Select("SELECT Y.*, num FROM ("
			+ " SELECT X.*, rownum as num FROM ("
				+ " SELECT id, name, cal"
				+ " FROM ingredient"
				+ " ORDER BY id ASC) X ) Y"
			+ " WHERE num BETWEEN #{start} AND #{end}")
	public List<IngredientVO> selectIngrList(Map map);
	
	@Select("SELECT month FROM ingr_season WHERE ingredient_id=#{id}")
	public List<Integer> selectIngrSeason();
	
	@Select("SELECT id, name, ingredient_id"
			+ " FROM ingr_religion i, religion r"
			+ " WHERE i.religion_id=r.id AND ingredient_id=#{id}")
	public List<ReligionVO> selectIngrReligion();
	
	@Select("SELECT id, name, ingredient_id"
			+ " FROM ingr_vegeterian i, vegeterian v"
			+ " WHERE i.vegeterian_id=v.id AND ingredient_id=#{id}")
	public List<VegeterianVO> selectIngrVegeterian();
	
	@Insert("INSERT INTO ingredient VALUES(ingredient_seq.nextval,#{name},#{cal})")
	@Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
	public int insertIngr(IngredientVO vo);
	
	@Insert("INSERT INTO ingr_season VALUES(ingr_season_seq.nextval,#{ingr_id},#{month})")
	public void insertIngrSeason(Map map);
	
	// 데이터 항목이 동일해서 종교VO로 받아옴
	@Select("SELECT id, name FROM ${tablename}")
	public List<ReligionVO> selectCatInfo(Map map);
	
	// 재료 분류 최상위 카테고리 데이터 변경
	@Insert("INSERT INTO religion VALUES(religion_seq.nextval,#{name})")
	public void insertReligion(String name);
	
	@Delete("DELETE FROM religion WHERE id = #{id}")
	public void deleteReligion(int id);
	
	@Insert("INSERT INTO vegeterian VALUES(vegeterian_seq.nextval,#{name})")
	public void insertVegeterian(String name);
	
	@Delete("DELETE FROM vegeterian WHERE id = #{id}")
	public void deleteVegeterian(int id);
	
}
