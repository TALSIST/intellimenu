package com.sist.restaurant;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.RestaurantReplyVO;

public interface ResreplyMapper {
	@Select("SELECT * FROM restaurant_reply where restaurant_id=#{restaurant_id} ORDER BY id")
	public List<RestaurantReplyVO> list(int restaurant_id);
	
	@Insert("INSERT INTO restaurant_reply(id, user_id, restaurant_id, reply, score, regdate, img_ori, img_new) "
			+ "VALUES(restaurant_reply_SEQ.nextval, #{user_id}, #{restaurant_id}, #{reply}, #{score}, SYSDATE, #{img_ori}, #{img_new})")
	public void insert(RestaurantReplyVO vo);

	@Update("Update restaurant_reply set report=(report+1) where id=#{id}")
	public void report(int id);
	
	//전체레코드 수 가져오기
	@Select("SELECT count(*) from restaurant_reply WHERE restaurant_id=#{restaurant_id}")
	public int totalRecord(int restaurant_id);
	
	//총페이지수 가져오기
	@Select("SELECT CEIL(COUNT(*)/9) FROM restaurant_reply WHERE restaurant_id=#{restaurant_id}")
	public int totalPage(int restaurant_id);

	
	/* 
	  @Insert("INSERT INTO RestaurantReply VALUES("
				 +"dr_no_seq.nextval,#{user_id},#{restaurant_id},"
				 +"#{reply},#{score},SYSDATE,#{group_id},"
				 +"#{group_step},#{group_tab},#{root},0)")
	  public void replyReInsert(RestaurantReplyVO vo);
	  
	  @Update("UPDATE RestaurantReply SET "
			 +"depth=depth+1 "
			 +"WHERE id=#{id}")
	  public void replyDepthIncrement(int id);
	  
 // ����
	  @Select("SELECT depth,root FROM RestaurantReply "
			 +"WHERE id=#{id}")
	  public RestaurantReplyVO replyGetDepthData(int id);
	  @Delete("DELETE FROM RestaurantReply "
			 +"WHERE id=#{id}")
	  public void replyDelete(int id);
	  @Update("UPDATE RestaurantReply SET "
			 +"reply='?��?��?�� ?��?��?��?��겠습?���?' "
			 +"WHERE id=#{id}")
	  public void replyMsgUpdate(int id);
	  
	  @Update("UPDATE RestaurantReply SET "
				 +"depth=depth-1 "
				 +"WHERE id=#{id}")
	  public void replyDepthDecrement(int id);
	                      gi   gs   gt   root
	   *  1  AAAAA            1   0     0    0
	   *  2   -> BBBBBB       1   1     1    1
	   *  3    -> CCCCCCC     1   2     2    2
	   *         ->           1   3     3    3
	   *  4   -> DDDDD        1   3     1    1
	   *  5    -> FFFFF       1   4     2    4
	   *        
	   *  6 FFFFFF            2   0     0    0
	   
	  @Delete("DELETE FROM RestaurantReply "
			 +"WHERE restaurant_id=#{restaurant_id}")
	  public void replyAllDelete(int restaurant_id);*/
}
