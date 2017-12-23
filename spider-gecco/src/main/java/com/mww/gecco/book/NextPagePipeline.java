package com.mww.gecco.book;

import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.scheduler.DeriveSchedulerContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;
 

/**
 * @description:
 * @author rook
 * @date 2016-4-8 下午10:18:11
 */

public class NextPagePipeline implements Pipeline<SecondPage> {


	public void process(SecondPage bean) {
		// 保存当前页爬取的书籍信息
		List<Book> books = bean.getBooks();
		for (Book book : books) {
			//继续抓取详情页
			HttpRequest sub = bean.getRequest().subRequest(book.getHref());
//			sub.addParameter("txtinfoId", ""+book.);
//			sub.addParameter("type", categoryServiceImpl.selectByPrimaryKey(Integer.parseInt(bookInfoService.selectByPrimaryKey(bookInfo.getId()).getTag())).getTag());
			DeriveSchedulerContext.into(sub);
		}
		//继续抓取下一页构造下一页
		int currPage = bean.getCurrPage();
		int totalPage = getTotalPage(bean.getLastPageUrl());
		String tagCode = bean.getTagCode();
		if (currPage < totalPage) {
			int nextPage = currPage + 1;
			String nextUrl = "http://www.aqtxt.com/" + tagCode +"/"+nextPage+".htm";
			HttpRequest request = bean.getRequest();
			DeriveSchedulerContext.into(request.subRequest(nextUrl));
		}
	}
	
	private int getTotalPage(String lastPageUrl) {
		if(StringUtils.isEmpty(lastPageUrl)) {
			return 1;
		}
		//http://www.meizitu.com/tag/ suxiong_17_ 9 .html
		lastPageUrl = StringUtils.substringAfterLast(lastPageUrl, "/");
		lastPageUrl = StringUtils.substringBefore(lastPageUrl, ".");
		return NumberUtils.toInt(lastPageUrl, 1);
	}
}
