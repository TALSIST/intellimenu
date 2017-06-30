package com.sist.restaurant;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.RestaurantReplyVO;

import java.util.*;
public interface ResreplyMapper {
	@Select("SELECT * from restaurant_reply where restaurant_id=#{restaurantid}")
	public List<RestaurantReplyVO> list(int restaurantid);
	
	@Insert("INSERT INTO restaurant_reply VALUES(restaurant_reply_SEQ.nextval,#{userid},#{restaurantid},#{reply},#{score},SYSDATE,'null','null')")
	public void insert(RestaurantReplyVO vo);
	
/*  @Select("SELECT COUNT(*) FROM RestaurantReply "
		 +"WHERE restaurant_id=#{restaurant_id}")
  public int replyCount(int restaurant_id);
  
  @Select("SELECT id,user_id,restaurant_id,reply,score,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS'),group_tab "
		 +"FROM RestaurantReply WHERE restaurant_id=#{restaurant_id} ORDER BY group_id DESC,group_step ASC")
  public List<RestaurantReplyVO> replyListData(int restaurant_id);
  
  @Insert("INSERT INTO RestaurantReply VALUES("
		 +"dr_no_seq.nextval,#{user_id},#{restaurant_id},"
		 +"#{reply},#{score},SYSDATE,(SELECT NVL(MAX(group_id)+1,1) FROM RestaurantReply),"
		 +"0,0,0,0)")
  public void replyNewInsert(RestaurantReplyVO vo);
  
  @Update("UPDATE RestaurantReply SET "
		 +"reply=#{reply} "
		 +"WHERE id=#{id}")
  public void replyUpdate(RestaurantReplyVO vo);
  // ���? => ���?
  @Select("SELECT group_id,group_step,group_tab "
		 +"FROM RestaurantReply "
		 +"WHERE id=#{id}")
  public RestaurantReplyVO replyParentInfoData(int id);
  
  @Update("UPDATE RestaurantReply SET "
		 +"group_step=group_step+1 "
		 +"WHERE group_id=#{group_id} "
		 +"AND group_step>#{group_step}")
  public void replyStepIncrement(RestaurantReplyVO vo);

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

