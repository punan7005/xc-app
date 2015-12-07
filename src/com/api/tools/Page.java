package com.api.tools;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/** 系统分页显示封装类 */
@SuppressWarnings( { "unchecked", "serial" })
public class Page implements Serializable {

	private List list;

	/** 默认每页记录数为10 */
	public static final int DEFAULT_PAGE_ROW_COUNT = 10;

	/** 总记录数 */
	private int rowCount = 0;
	/** 当前页码 */
	private int currentPageIndex = 1;
	/** 每页显示记录数 */
	private int pageRowCount = DEFAULT_PAGE_ROW_COUNT;
	/** 总页数 */
	private int pageCount = 0;
	/** 起始记录索引 */
	private int startIndex = 0;

	private String jumpFunction = "";

	/**
	 * 根据当前页索引和记录总数改造类。
	 *
	 * @param currentPageIndex 当前页索引
	 * @param rowCount 记录总数
	 */
	public Page(final int currentPageIndex, final int rowCount) {
		this(currentPageIndex, DEFAULT_PAGE_ROW_COUNT, rowCount);
	}

	/**
	 * 根据当前页索引、每页记录数、记录总数构造类。
	 *
	 * @param currentPageIndex 当前页索引
	 * @param pageRowCount 每页记录数
	 * @param rowCount 记录总数
	 */
	public Page(final int currentPageIndex, final int pageRowCount, final int rowCount) {
		this.currentPageIndex = currentPageIndex;
		if (pageRowCount <= 0)
			this.pageRowCount = DEFAULT_PAGE_ROW_COUNT;
		else
			this.pageRowCount = pageRowCount;
		this.rowCount = rowCount;
		calParam();
	}

	/**
	 * 计算参数。
	 * 根据构造方法传递的参数计算其他参数，如总页数、起始记录索引等。
	 */
	private void calParam() {
		// 总页数
		if (0 < pageRowCount) {
			pageCount = rowCount / pageRowCount;
			// 若页数与每页行数之积小于总行数，则页数加一
			if (rowCount > pageCount * pageRowCount) {
				pageCount = pageCount + 1;
			}
		}
		if (0 >= pageCount) {
			pageCount = 1;
		}
		if (0 >= currentPageIndex) {
			currentPageIndex = 1;
		}
		// 当前页违规捕获
		if (0 > currentPageIndex && currentPageIndex > pageCount) {// 当前页小于零，并且当前页大于总页数
			currentPageIndex = 1;
		}

		if (0 < pageCount) {
			if (currentPageIndex <= pageCount) {
				startIndex = (currentPageIndex - 1) * getPageRowCount();
			} else {
				currentPageIndex = pageCount;
				startIndex = (currentPageIndex - 1) * getPageRowCount();
			}
		}
		// 若起始页为负，则置为0
		if (0 > startIndex) {
			startIndex = 0;
		}
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
		calParam();
	}

	public int getCurrentPageIndex() {
		return currentPageIndex;
	}

	public void setCurrentPageIndex(int currentPageIndex) {
		this.currentPageIndex = currentPageIndex;
		calParam();
	}

	public int getPageRowCount() {
		return pageRowCount;
	}

	public void setPageRowCount(int pageRowCount) {
		this.pageRowCount = pageRowCount;
		calParam();
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
		calParam();
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
		calParam();
	}

	public String getJumpFunction() {
		return jumpFunction;
	}

	public void setJumpFunction(String jumpFunction) {
		this.jumpFunction = jumpFunction;
	}

	/**
	 * 取上一页页码。
	 *
	 * @return 前一页页码，若小于等于零，则返回1
	 */
	public int getPreviousePage() {
		int prevPage = this.currentPageIndex - 1;
		if (0 >= prevPage) {
			prevPage = 1;
		}
		return prevPage;
	}

	/**
	 * 取下一页页码。
	 *
	 * @return 下一页页码，若大于总页数，则返回最后一页页码
	 */
	public int getNextPage() {
		int nextPage = this.currentPageIndex + 1;
		if (pageCount < nextPage) {
			nextPage = pageCount;
		}
		return nextPage;
	}// end nextPage()

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
		if (pageRowCount <= 0 && list != null)
			setRowCount(list.size());
	}

	public int getNextPageNumber() {
		int nn;
		if (currentPageIndex < pageCount) {
			nn = currentPageIndex + 1;
		} else {
			nn = pageCount;
		}
		return nn;
	}

	public int getPrevPageNumber() {
		int pn;
		if (currentPageIndex > 1) {
			pn = currentPageIndex - 1;
		} else {
			pn = 1;
		}
		return pn;
	}
	
	/**
	 * 取页码字串。 本方法输出格式为：
	 * [1][2][...][n]
	 *
	 * 可在子类重通过覆盖此类或新增处理方法来返回自定义的输出格式。
	 *
	 * @param strHref Href链接串
	 * @return 格式化后的页码字串
	 */
	public String getPageString() {
		/*
		StringBuffer pages = new StringBuffer();

		if(currentPageIndex==1){
			pages.append("<span class=\"disabled\"> < </span>");
		}else{
			pages.append("<a href=\"javascript:"+jumpFunction+"("+(currentPageIndex-1)+");\"> < </a>");
		}
		for(int i=1;i<=pageCount;i++){
			if(i==currentPageIndex){
				pages.append("<span class=\"current\">"+currentPageIndex+"</span>");
			}else{
				pages.append("<a href=\"javascript:"+jumpFunction+"("+i+");\"> "+i+" </a>");
			}
		}

		if(currentPageIndex==pageCount){
			pages.append("<span class=\"disabled\"> > </span>");
		}else{
			pages.append("<a href=\"javascript:"+jumpFunction+"("+(currentPageIndex+1)+");\"> > </a>");
		}
		
		return pages.toString();
		*/
		return getPageString(this.getJumpFunction(),this.getCurrentPageIndex(),this.getPageCount());
	}

	public static String getPageString(String jsMethod, int ec_p, int pageCount) {
		if (StringUtils.isBlank(jsMethod))
			jsMethod = "jump";
		StringBuffer str = new StringBuffer();
		if (pageCount == -1) {
			str.append("无记录");
			return str.toString();
		}
		if (ec_p != 1 && pageCount > 1) {
			str.append("<a class=\"current\" href='javascript:").append(jsMethod).append("(1)'>首页</a>");
			str.append("<a class=\"current\" href='javascript:").append(jsMethod).append("(" + (ec_p - 1) + ")'>上一页</a>");
		}
		if (pageCount <= 5 && pageCount >= 2) {
			str = getPageString(str, jsMethod, 1, pageCount, ec_p);
		} else if (pageCount != 1) {
			if (ec_p <= 2) {
				str = getPageString(str, jsMethod, 1, 5, ec_p);
			} else if (ec_p > 2 && ec_p < pageCount - 2) {
				str = getPageString(str, jsMethod, ec_p - 2, ec_p + 2, ec_p);
			} else {
				str = getPageString(str, jsMethod, pageCount - 4, pageCount, ec_p);
			}
		}
		if (ec_p != pageCount && pageCount > 1) {
			str.append("<a class=\"current\" href='javascript:").append(jsMethod).append("(" + (ec_p + 1) + ")'>下一页</a>");
//			str.append("<a class=\"current\" href='javascript:").append(jsMethod).append("(" + pageCount + ")'>尾页</a>");
		}
		if(ec_p==pageCount && pageCount ==1){
			str.append("<span class=\"current\">").append(ec_p).append("</span>");
		}
		return str.toString();
	}

	private static StringBuffer getPageString(StringBuffer str, String jsMethod, int sta, int end, int ec_p) {
		for (int i = sta; i <= end; i++) {
			if (i != ec_p)
				str.append("<a class=\"current\" href='javascript:").append(jsMethod).append("(" + i + ")'>" + i + "</a>");
			else
				str.append("<strong>" + i + "</strong>");
		}
		return str;
	}

	public String getPageClazzString() {
		return getPageString2(this.getJumpFunction(),this.getCurrentPageIndex(),this.getPageCount());
	}
	
	public static String getPageString2(String jsMethod, int ec_p, int pageCount) {
		if (StringUtils.isBlank(jsMethod))
			jsMethod = "jump";
		StringBuffer str = new StringBuffer();
		if (pageCount == -1) {
			str.append("无记录");
			return str.toString();
		}
		
		if(ec_p ==1){
			if(pageCount==1){
				str.append("<div class=\"startpagegray\"></div><div class=\"prepagegray\"></div>");
				str.append("<div class=\"nextpagegray\"></div><div class=\"endpagegray\"></div>");
			}else if(pageCount>1){
				str.append("<div class=\"startpagegray\"></div><div class=\"prepagegray\"></div>");
				str.append("<a href='javascript:").append(jsMethod).append("(" + (ec_p + 1) + ")'><div class=\"nextpage\"></div></a>");
				str.append("<a href='javascript:").append(jsMethod).append("(" + pageCount + ")'><div class=\"endpage\"></div></a>");
			}
		}else if(ec_p>1){
			if(ec_p==pageCount){
				str.append("<a href='javascript:").append(jsMethod).append("(1)'><div class=\"startpage\"></div></a>");
				str.append("<a href='javascript:").append(jsMethod).append("(" + (ec_p - 1) + ")'><div class=\"prepage\"></div></a>");
				str.append("<div class=\"nextpagegray\"></div><div class=\"endpagegray\"></div>");
			}else {
				str.append("<a href='javascript:").append(jsMethod).append("(1)'><div class=\"startpage\"></div></a>");
				str.append("<a href='javascript:").append(jsMethod).append("(" + (ec_p - 1) + ")'><div class=\"prepage\"></div></a>");
				str.append("<a href='javascript:").append(jsMethod).append("(" + (ec_p + 1) + ")'><div class=\"nextpage\"></div></a>");
				str.append("<a href='javascript:").append(jsMethod).append("(" + pageCount + ")'><div class=\"endpage\"></div></a>");
			}
		}
				
		return str.toString();
	}
	
public String getPageStringForJokerBase(){
		
		return getPageString3(this.getJumpFunction(),this.getCurrentPageIndex(),this.getPageCount(),this.getRowCount());
	}
	
	public String getPageString3(String jsMethod, int ec_p, int pageCount, int rowCount){
		if (StringUtils.isBlank(jsMethod))
			jsMethod = "jump";
		StringBuffer str = new StringBuffer();
		if (pageCount == -1) {
			str.append("无记录");
			return str.toString();
		}
		
		str.append("<span class=\"pageRecordClass\" id=\"pageRecord\">");
		str.append("[总数:<b>" + rowCount + "</b> 条</span>] ");
		if(ec_p == 1){
			if(pageCount == 1){
				str.append("[<a href='#'>首页</a>] ");
				str.append("[<a href='#'>上一页</a>] ");
				str.append("[<a href='#'>下一页</a>] ");
				str.append("[<a href='#'>尾页</a>] ");
			}else if(pageCount > 1){
				str.append("[<a href='#'>首页</a>] ");
				str.append("[<a href='#' style='color:#666;'>上一页</a>] ");
				str.append("[<a href='javascript:").append(jsMethod).append("(" + (ec_p + 1) + ")')>下一页</a>] ");
				str.append("[<a href='javascript:").append(jsMethod).append("(" + pageCount + ")'>尾页</a>] ");
			}
		}else if(ec_p > 1){
			if(pageCount == ec_p){
				str.append("[<a href='javascript:").append(jsMethod).append("(1)'>首页</a>] ");
				str.append("[<a href='javascript:").append(jsMethod).append("(" + (ec_p - 1) + ")'>上一页</a>] ");
				str.append("[<a href='#' style='color:#666;'>下一页</a>] ");
				str.append("[<a href='#'>尾页</a>] ");
			}else{
				str.append("[<a href='javascript:").append(jsMethod).append("(1)'>首页</a>] ");
				str.append("[<a href='javascript:").append(jsMethod).append("(" + (ec_p - 1) + ")'>上一页</a>] ");
				str.append("[<a href='javascript:").append(jsMethod).append("(" + (ec_p + 1) + ")')>下一页</a>] ");
				str.append("[<a href='javascript:").append(jsMethod).append("(" + pageCount + ")'>尾页</a>] ");
			}
		}
		
//		str.append("转到第 <input type=\"text\" style=\"width=30px;\" name=\"jumpno\" id=\"jumpno\" value=\"\"  />");
		str.append("[当前第" + ec_p + "页/总" + pageCount + "页]"+"<input type='hidden' id='ec_p' value='"+ec_p+"'>");
		return str.toString();
	}
}
