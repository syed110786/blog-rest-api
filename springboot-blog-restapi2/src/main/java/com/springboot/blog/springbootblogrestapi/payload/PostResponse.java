package com.springboot.blog.springbootblogrestapi.payload;

import java.util.List;

public class PostResponse {

	private List<PostDTO> content;
	private int pageNo;
	private int pageSize;
	private Long totalElements;
	private int totalPages;
	private boolean last;
	public List<PostDTO> getContent() {
		return content;
	}
	public int getPageNo() {
		return pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public Long getTotalElements() {
		return totalElements;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public boolean isLast() {
		return last;
	}
	public void setContent(List<PostDTO> content) {
		this.content = content;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public void setLast(boolean last) {
		this.last = last;
	}
	
	
}
