package com.sist.util;

import java.util.HashMap;
import java.util.Map;

public class PagingManager {
	/**
	 * Page 처리
	 */
	private int rowSize = 15; // 한 화면에 표시할 행
	private int total; // 전체 게시물 수
	private int start; // 출력 화면의 시작 행
	private int end; // 출력 화면의 종료 행
	
	/**
	 * Block 처리
	 */
	private int blockSize = 10;
	private int totalPage; // 전체 페이지 수
	private int startBlock; // block의 시작
	private int endBlock; // block의 마지막
	private int prevBtn; // 이전 block 버튼
	private int nextBtn; // 다음 block 버튼
	
	/**
	 * 현재 페이지 (dispatcher가 값을 받아줌)
	 */
	private int page;
	
	public Map<String, Integer> calcPage(int total) {
		this.total = total;
		
		if (page == 0) {
			page = 1;
		}

		end = rowSize * page;
		start = end - rowSize + 1;
		
		Map<String, Integer> map = new HashMap();
		map.put("end", end);
		map.put("start", start);
		
		calcBlock(map);
		
		return map;
	}
	
	private void calcBlock(Map<String,Integer> map) {
		totalPage = (int) (Math.ceil((float)total/rowSize));
		startBlock = page - (page - 1) % blockSize;
		endBlock = startBlock + blockSize - 1;
		if (endBlock > totalPage) {
			endBlock = totalPage;
		}
		prevBtn = (startBlock==1)? 1: startBlock-1;
		nextBtn = (endBlock==totalPage)? totalPage: endBlock+1;
		
	}
	
	/**
	 * Getter/Setter 영역 
	 */
	public int getRowSize() {
		return rowSize;
	}

	public void setRowSize(int rowSize) {
		this.rowSize = rowSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartBlock() {
		return startBlock;
	}

	public void setStartBlock(int startBlock) {
		this.startBlock = startBlock;
	}

	public int getEndBlock() {
		return endBlock;
	}

	public void setEndBlock(int endBlock) {
		this.endBlock = endBlock;
	}

	public int getPrevBtn() {
		return prevBtn;
	}

	public void setPrevBtn(int prevBtn) {
		this.prevBtn = prevBtn;
	}

	public int getNextBtn() {
		return nextBtn;
	}

	public void setNextBtn(int nextBtn) {
		this.nextBtn = nextBtn;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

}
