package com.sist.recipe;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.CatSubVO;
import com.sist.vo.IngredientVO;
import com.sist.vo.ReligionVO;
import com.sist.vo.VegeterianVO;

public interface IngredientMapper {
	
	// 재료 전체 수량 조회
	@Select("SELECT COUNT(*) FROM ingredient")
	public int selectIngrTotal();
	
	// 재료 전체를 가져옴
	@Select("SELECT title FROM ingredient")
	public List<String> selectWholeIngredient();
	
	/**
	 * 전체 본문 조회
	 */
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
	@Select("SELECT * FROM ("
			+ " SELECT X.*, rownum as num FROM ("
				+ " SELECT id, name, cal"
				+ " FROM ingredient"
				+ " ORDER BY id ASC) X)"
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
	
	
	/**
	 * 조건 본문 조회 
	 */
	// 속성 보유 리스트 검색
	@Select("SELECT COUNT(*) FROM ingredient i"
			+ " WHERE EXISTS ("
				+ " SELECT s.ingredient_id,s.${idname}"
				+ " FROM ${tablename} s"
				+ " WHERE i.id = s.ingredient_id AND s.${idname}=#{sub})")
	public int selectIngrExistTotal(Map map);
			
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
	@Select("SELECT * FROM ("
			+ " SELECT X.*, rownum as num FROM ("
				+ " SELECT i.id,i.name,i.cal FROM ingredient i"
				+ " WHERE EXISTS ("
					+ " SELECT s.ingredient_id,s.${idname}"
					+ " FROM ${tablename} s"
					+ " WHERE i.id = s.ingredient_id AND s.${idname}=#{sub})"
				+ " ORDER BY id ASC) X)"
				+ " WHERE num BETWEEN #{start} AND #{end}")
	public List<IngredientVO> selectIngrExistList(Map map);
	
	// 속성 미보유 리스트 검색
	@Select("SELECT COUNT(*) FROM ingredient i"
			+ " WHERE NOT EXISTS ("
				+ " SELECT s.ingredient_id,s.${idname}"
				+ " FROM ${tablename} s"
				+ " WHERE i.id = s.ingredient_id AND s.${idname}=#{sub})")
	public int selectIngrNotExistTotal(Map map);
			
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
	@Select("SELECT * FROM ("
				+ " SELECT X.*, rownum as num FROM ("
					+ " SELECT i.id,i.name,i.cal FROM ingredient i"
					+ " WHERE NOT EXISTS ("
						+ " SELECT s.ingredient_id,s.${idname}"
						+ " FROM ${tablename} s"
						+ " WHERE i.id = s.ingredient_id AND s.${idname}=#{sub})"
					+ " ORDER BY id ASC) X)"
					+ " WHERE num BETWEEN #{start} AND #{end}")
	public List<IngredientVO> selectIngrNotExistList(Map map);
	
	
	/**
	 * 데이터 입력/삭제
	 */
	// 재료 입력
	@Insert("INSERT INTO ingredient VALUES(ingredient_seq.nextval,#{name},#{cal})")
	@Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
	public int insertIngr(IngredientVO vo);
	
	// 재료 삭제
	@Delete("DELETE FROM ingredient WHERE id=#{id}")
	public void deleteIngredient(Map map);
	
	// 카테고리 연결 추가
	@Insert("INSERT INTO ingr_religion VALUES(#{ingredient_id},#{id})")
	public void insertIngrReligion(Map map);

	@Insert("INSERT INTO ingr_vegeterian VALUES(#{ingredient_id},#{id})")
	public void insertIngrVegeterian(Map map);
	
	@Insert("INSERT INTO ingr_season VALUES(ingr_season_seq.nextval,#{ingredient_id},#{id})")
	public void insertIngrSeason(Map map);
	
	// 카테고리 연결 삭제
	@Delete("DELETE FROM ingr_religion WHERE ingredient_id=#{ingredient_id} AND religion_id=#{id}")
	public void deleteIngrReligion(Map map);
	
	@Delete("DELETE FROM ingr_vegeterian WHERE ingredient_id=#{ingredient_id} AND vegeterian_id=#{id}")
	public void deleteIngrVegeterian(Map map);
	
	@Delete("DELETE FROM ingr_season WHERE id=#{ingredient_id} AND month=#{id}")
	public void deleteIngrSeason(Map map);
	
	
	/**
	 * 카테고리 데이터 조회/변경 
	 */
	// 데이터 항목이 동일해서 catsubVO로 받아옴
	@Select("SELECT id, name FROM ${tablename}")
	public List<CatSubVO> selectCatInfo(Map map);
	
	@Insert("INSERT INTO religion VALUES(religion_seq.nextval,#{name})")
	public void insertReligion(String name);
	
	@Insert("INSERT INTO vegeterian VALUES(vegeterian_seq.nextval,#{name})")
	public void insertVegeterian(String name);
	
	@Delete("DELETE FROM religion WHERE id = #{id}")
	public void deleteReligion(int id);
	
	@Delete("DELETE FROM vegeterian WHERE id = #{id}")
	public void deleteVegeterian(int id);
	
	// 카테고리 삭제 되면서 연결 데이터 일괄 삭제
	@Delete("DELETE FROM ingr_religion WHERE religion_id=#{id}")
	public void deleteReligionIngrLinked(int id);
	
	@Delete("DELETE FROM ingr_vegeterian WHERE vegeterian_id=#{id}")
	public void deleteVegeterianIngrLinked(int id);
	

	/**
	 * 재료 리스트 검색
	 */
	@Select("SELECT COUNT(*) FROM ingredient WHERE name LIKE '%'||#{keyword}||'%'")
	public int selectSearchIngrTotal(Map map);
	
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
	@Select("SELECT * FROM ("
			+ " SELECT X.*, rownum as num FROM ("
				+ " SELECT id, name, cal"
				+ " FROM ingredient"
				+ " WHERE name LIKE '%'||#{keyword}||'%'"
				+ " ORDER BY id ASC) X)"
			+ " WHERE num BETWEEN #{start} AND #{end}")
	public List<IngredientVO> selectSearchIngrList(Map map);
	
	// 속성 보유 리스트 검색
		@Select("SELECT COUNT(*) FROM ingredient i"
				+ " WHERE EXISTS ("
					+ " SELECT s.ingredient_id,s.${idname}"
					+ " FROM ${tablename} s"
					+ " WHERE i.id = s.ingredient_id AND s.${idname}=#{sub})"
				+ " AND name LIKE '%'||#{keyword}||'%'")
		public int selectSearchIngrExistTotal(Map map);
				
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
		@Select("SELECT * FROM ("
				+ " SELECT X.*, rownum as num FROM ("
					+ " SELECT i.id,i.name,i.cal FROM ingredient i"
					+ " WHERE EXISTS ("
						+ " SELECT s.ingredient_id,s.${idname}"
						+ " FROM ${tablename} s"
						+ " WHERE i.id = s.ingredient_id AND s.${idname}=#{sub})"
					+ " AND name LIKE '%'||#{keyword}||'%'"
					+ " ORDER BY id ASC) X)"
					+ " WHERE num BETWEEN #{start} AND #{end}")
		public List<IngredientVO> selectSearchIngrExistList(Map map);
		
		// 속성 미보유 리스트 검색
		@Select("SELECT COUNT(*) FROM ingredient i"
				+ " WHERE NOT EXISTS ("
					+ " SELECT s.ingredient_id,s.${idname}"
					+ " FROM ${tablename} s"
					+ " WHERE i.id = s.ingredient_id AND s.${idname}=#{sub})"
				+ " AND name LIKE '%'||#{keyword}||'%'")
		public int selectSearchIngrNotExistTotal(Map map);
				
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
		@Select("SELECT * FROM ("
					+ " SELECT X.*, rownum as num FROM ("
						+ " SELECT i.id,i.name,i.cal FROM ingredient i"
						+ " WHERE NOT EXISTS ("
							+ " SELECT s.ingredient_id,s.${idname}"
							+ " FROM ${tablename} s"
							+ " WHERE i.id = s.ingredient_id AND s.${idname}=#{sub})"
						+ " AND name LIKE '%'||#{keyword}||'%'"
						+ " ORDER BY id ASC) X)"
						+ " WHERE num BETWEEN #{start} AND #{end}")
		public List<IngredientVO> selectSearchIngrNotExistList(Map map);
	
		
	@Select("SELECT INGREDIENT.id AS id, name"
			+ " FROM INGREDIENT, INGR_SEASON"
			+ " WHERE INGREDIENT.id=INGR_SEASON.ingredient_id"
			+ " and MONTH=#{nowMonth}"
			+ " ORDER BY name asc")	
	public List<IngredientVO> selectIngrListByMonth(int nowMonth);
}
