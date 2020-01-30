package com.foresee.vo;

import java.util.List;

import com.foresee.pojo.Tag;

public class TagListVo {
	
	private List<Tag> tags;
	private List<TagVo> likeTags;
	private String tagType;
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	public List<TagVo> getLikeTags() {
		return likeTags;
	}
	public void setLikeTags(List<TagVo> likeTags) {
		this.likeTags = likeTags;
	}
	public String getTagType() {
		return tagType;
	}
	public void setTagType(String tagType) {
		this.tagType = tagType;
	}
	
	

}
