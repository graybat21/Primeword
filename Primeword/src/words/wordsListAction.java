package words;

import com.opensymphony.xwork2.ActionSupport;

import words.wordsVO;
//import notice.pagingAction;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import java.util.*;
import java.io.Reader;
import java.io.IOException;

public class wordsListAction  extends ActionSupport  {
	public static Reader reader;
	public static SqlMapClient sqlMapper;
	
//	private Map session;
	public wordsVO resultClass;
	
	private List<wordsVO> list = new ArrayList<wordsVO>();
	
	private int no;
	private int lev;
	private int section;
	private int part;
	private String word;
	private String meaning;

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getLev() {
		return lev;
	}

	public void setLev(int lev) {
		this.lev = lev;
	}

	public int getSection() {
		return section;
	}

	public void setSection(int section) {
		this.section = section;
	}

	public int getPart() {
		return part;
	}

	public void setPart(int part) {
		this.part = part;
	}

	
	public wordsListAction() throws IOException{
		reader = Resources.getResourceAsReader("sqlMapConfig.xml");
		sqlMapper = SqlMapClientBuilder.buildSqlMapClient(reader);
		reader.close();
		
	}
	
	public String execute() throws Exception{

//		System.out.println(lev);
//		resultClass.setLev(lev);
//		resultClass.setSection(section);
//		resultClass.setPart(part);
		
		no = 720 *(lev-1)+120*(section-1)+20*(page-1);
		list = sqlMapper.queryForList("selectOne",no);
		
//		totalCount = list.size();
//		page = new pagingAction(currentPage, totalCount, blockCount, blockPage, num, "");
//		pagingHtml = page.getPagingHtml().toString();
//		
//		
//		//현재 페이지에서 보여줄 마지막 글의 번호
//		int lastCount = totalCount;
//		
//		// 현재 페이지의 마지막 글의 번호가 전체의 마지막 글 번호보다 작으면 
//				//lastCount를 +1 번호로 설정.
//		if(page.getEndCount() < totalCount)
//			lastCount = page.getEndCount() +1;
//		
//		list = list.subList(page.getStartCount(), lastCount);
		
		return SUCCESS;
	}

	public List<wordsVO> getList() {
		return list;
	}

	public void setList(List<wordsVO> list) {
		this.list = list;
	}

	
}
