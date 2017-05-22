package com.prime.common;

public class PageMaker {

	public Integer page;
	public Integer count;
	public Integer start;
	public Integer end;
	public boolean prev;
	public boolean next;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		if (page < 1 || page == null) {
			this.page = 1;
			return;
		}
		this.page = page;
	}

	public void setCount(Integer count, int countPerPage, int countPerPaging) {
		if (count < 1) {
			return;
		}
		this.count = count;
		calcPage(countPerPage, countPerPaging);
	}

	private void calcPage(int countPerPage, int countPerPaging) {
		// page변수는 현재 페이지번호, 현재 페이지번호를 기준으로 끝 페이지를 계산한다.
		int tempEnd = (int) (Math.ceil(page / (float) countPerPaging) * countPerPaging);
		// 시작 페이지 계산
		this.start = tempEnd - countPerPaging + 1;// 1

		this.end = (int) Math.ceil(this.count / (float) countPerPage);
		if (tempEnd * countPerPage < this.count){
			this.end = tempEnd;
		}
		this.prev = this.start == 1 ? false : true;
		this.next = this.end * countPerPage >= this.count ? false : true;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public Integer getCount() {
		return count;
	}

	@Override
	public String toString() {
		return "PageMaker [page=" + page + ", count=" + count + ", start=" + start + ", end=" + end + ", prev=" + prev
				+ ", next=" + next + "]";
	}

}
// -------------controller------------
// @RequestMapping(value="/noticeList.gh")
// public String noticeList(PageMaker pagemaker,Model model) throws Exception{
// int page = 1;
// int totalCnt = 0;
// int countPerPage = 5;
// int countPerPaging = 5;
//
// page = pagemaker.getPage() != null ? pagemaker.getPage() : 1;
// pagemaker.setPage(page);
//
// totalCnt = noticeService.selectListCnt(); // DB연동_ 총 갯수 구해오기
// pagemaker.setCount(totalCnt, countPerPaging);
//
// int first = ((pagemaker.getPage() - 1) * countPerPage) + 1;
// int last = first + countPerPage - 1;
//
// HashMap<String, Object> map = new HashMap<String, Object>();
// map.put("first", first);
// map.put("last", last);
// List<NoticeVO> list = noticeService.noticeList(map);
//
// model.addAttribute("noticeList", list);
// model.addAttribute("noticePageMaker", pagemaker);
//
// return "notice/noticeList/공지사항";
// }
//
// ------------jsp-------------
// <div class="svc_paging">
// <ul class="pageUL">
// <c:if test="${noticePageMaker.prev }">
// <li><a href='noticeList.gh?page=${noticePageMaker.start -1}'>이전</a></li>
// </c:if>
// <c:forEach begin="${noticePageMaker.start }" end="${noticePageMaker.end}"
// var="idx">
// <li
// class='<c:out value="${idx == noticePageMaker.page?'current':''}"/>'>
// <a href='noticeList.gh?page=${idx}'>${idx}</a>
// </li>
// </c:forEach>
// <c:if test="${noticePageMaker.next }">
// <li><a href='noticeList.gh?page=${noticePageMaker.end +1}'>다음</a></li>
// </c:if>
// </ul>
// </div>
//
// --------------sql----------------
// <select id="noticeList" resultType="noticeVO" parameterType="hashmap">
// select * from
// (SELECT ROW_NUMBER() OVER(ORDER BY no DESC) RNUM,
// no,
// content,
// subject,
// name,
// reg
// FROM NOTICE
// ) X
// where X.RNUM between #{first} and #{last}
// </select>