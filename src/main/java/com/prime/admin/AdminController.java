package com.prime.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.prime.common.PageMaker;
import com.prime.remember.Remember;
import com.prime.study.Study;
import com.prime.user.User;

@Controller
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Inject
	private AdminService adminService;

	// admin 메인 페이지
	@RequestMapping(value = "/admin/main.prime")
	public String adminMain() {
		return "admin/mainAdmin/관리자 메인 페이지";
	}

	// admin 유져관리페이지
	@RequestMapping(value = "/admin/userManagement.prime")
	public ModelAndView adminUser(PageMaker pagemaker, @RequestParam(value = "o", required = false) String searchOption,
			@RequestParam(value = "k", required = false) String searchKeyword) throws Exception {
		ModelAndView mav = new ModelAndView("admin/userList/유저 관리 페이지");
		HashMap<String, Object> map = new HashMap<String, Object>();

		int page = pagemaker.getPage() != null ? pagemaker.getPage() : 1;
		pagemaker.setPage(page);
		map.put("searchOption", searchOption);
		map.put("searchKeyword", searchKeyword);
		int totalCnt = adminService.userListCnt(map); // DB연동_ 총 갯수 구해오기
		int countPerPage = 5;
		int countPerPaging = 3;

		int first = ((pagemaker.getPage() - 1) * countPerPage) + 1;
		int last = first + countPerPage - 1;

		map.put("first", first);
		map.put("last", last);

		List<User> userList = adminService.userList(map);
		pagemaker.setCount(totalCnt, countPerPage, countPerPaging);
		mav.addObject("userList", userList);
		mav.addObject("pageMaker", pagemaker);
		mav.addObject("searchOption", searchOption);
		mav.addObject("searchKeyword", searchKeyword);

		return mav;
	}

	// 관리자의 회원관리 - 회원 강제 탈퇴
	@RequestMapping("/admin/userDelete.prime")
	public ModelAndView userDelete(HttpServletRequest request) throws Exception {

		ModelAndView mav = new ModelAndView();
		int no = Integer.parseInt(request.getParameter("no"));
		adminService.userDelete(no);

		mav.setViewName("redirect:/admin/userManagement.prime");
		return mav;
	}
	
	// 관리자의 회원관리 - 회원 상세보기
	@RequestMapping("/admin/userDetail.prime")
	public ModelAndView userDetail(HttpServletRequest request) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		int no = Integer.parseInt(request.getParameter("no"));
		User user = adminService.userDetail(no);
		List<Remember> remember= adminService.knownWordsByUserNo(no);
		
		mav.addObject(user);
		mav.addObject("remember", remember);
				
		mav.setViewName("admin/userDetail/상세보기");
		
		logger.info(remember.toString());
		return mav;
	}

	// 관리자의 단어관리
	@RequestMapping(value = "/admin/wordsManagement.prime")
	public ModelAndView adminWords(PageMaker pagemaker, @RequestParam(value = "o", required = false) String searchOption,
			@RequestParam(value = "k", required = false) String searchKeyword) throws Exception {
		ModelAndView mav = new ModelAndView("admin/wordsList/단어 관리 페이지");
		HashMap<String, Object> map = new HashMap<String, Object>();

		int page = pagemaker.getPage() != null ? pagemaker.getPage() : 1;
		pagemaker.setPage(page);
		map.put("searchOption", searchOption);
		map.put("searchKeyword", searchKeyword);
		int totalCnt = adminService.wordsListCnt(map); // DB연동_ 총 갯수 구해오기
		int countPerPage = 10;
		int countPerPaging = 5;

		int first = ((pagemaker.getPage() - 1) * countPerPage) + 1;
		int last = first + countPerPage - 1;

		map.put("first", first);
		map.put("last", last);

		List<Study> wordsList = adminService.wordsList(map);
		pagemaker.setCount(totalCnt, countPerPage, countPerPaging);
		mav.addObject("wordsList", wordsList);
		mav.addObject("pageMaker", pagemaker);
		mav.addObject("searchOption", searchOption);
		mav.addObject("searchKeyword", searchKeyword);
		mav.addObject("totalCount",totalCnt);
		return mav;
	}

	// 한단어 추가
	@RequestMapping("/admin/insertWord.prime")
	public String wordInsert(Study study)throws Exception{
		adminService.wordInsert(study);
		return "redirect:/admin/wordsManagement.prime";
	}
	// 한단어 삭제
	@RequestMapping("/admin/wordDelete.prime")
	public ModelAndView wordDelete(HttpServletRequest request) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		int no = Integer.parseInt(request.getParameter("no"));
		adminService.wordDelete(no);
		
		mav.setViewName("redirect:/admin/wordsManagement.prime");
		return mav;
	}
	// 단어그룹 삭제
	@RequestMapping("/admin/wordsGroupDelete.prime")
	public ModelAndView wordsGroupDelete(Study study) throws Exception {
		
		ModelAndView mav = new ModelAndView();
//		Study study = new Study();
//		study.setGrade(request.getParameter("grade"));
//		study.setTextbook(request.getParameter("textbook"));
//		study.setLesson(Integer.parseInt(request.getParameter("lesson")));
		adminService.wordsGroupDelete(study);
		
		mav.setViewName("redirect:/admin/wordsGroupManagement.prime");
		return mav;
	}
	
	@RequestMapping("/admin/wordsGroupManagement.prime")
	public ModelAndView adminWordsGroup(PageMaker pagemaker, @RequestParam(value = "o", required = false) String searchOption,
			@RequestParam(value = "k", required = false) String searchKeyword) throws Exception {
		ModelAndView mav = new ModelAndView("admin/wordsGroupList/단어 그룹 관리 페이지");
		HashMap<String, Object> map = new HashMap<String, Object>();

		int page = pagemaker.getPage() != null ? pagemaker.getPage() : 1;
		pagemaker.setPage(page);
		map.put("searchOption", searchOption);
		map.put("searchKeyword", searchKeyword);
		int totalCnt = adminService.wordsGroupListCnt(map); // DB연동_ 총 갯수 구해오기
		int countPerPage = 5;
		int countPerPaging = 5;

		int first = ((pagemaker.getPage() - 1) * countPerPage) + 1;
		int last = first + countPerPage - 1;

		map.put("first", first);
		map.put("last", last);

		List<Study> wordsGroupList = adminService.wordsGroupList(map);
		pagemaker.setCount(totalCnt, countPerPage, countPerPaging);
		mav.addObject("wordsGroupList", wordsGroupList);
		mav.addObject("pageMaker", pagemaker);
		mav.addObject("searchOption", searchOption);
		mav.addObject("searchKeyword", searchKeyword);
		mav.addObject("totalCount",totalCnt);
		
		return mav;
	}
	
//	@RequestMapping(value = "/admin/wordsList.prime")
//	public ModelAndView adminWordsListBySearch(@RequestParam("searchKeyword") String searchKeyword,
//			@RequestParam("searchOption") String searchOption) throws Exception {
//		ModelAndView mav = new ModelAndView();
//		if (searchKeyword == null) {
//			mav.setViewName("redirect:/admin/wordsManagement.prime");
//			return mav;
//		}
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("searchOption", searchOption);
//		map.put("searchKeyword", searchKeyword);
//		logger.info(map.toString());
//		List<Study> wordsList = adminService.adminWordsListBySearch(map);
//		logger.info(wordsList.toString());
//		mav.addObject("wordsList", wordsList);
//		mav.setViewName("admin/wordsList/단어 관리 페이지");
//		return mav;
//	}

//	@ResponseBody
//	@RequestMapping("/admin/textbookCondition.prime")
//	public List<String> adminConditionTextbook(@RequestBody String grade) throws Exception {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("grade", grade);
//		List<String> textbookList = adminService.textbookList(map);
//
//		// ProductDAO dao = new ProductDAO();
//		// ArrayList<String> list =
//		// dao.getSubList(request.getParameter("product"));
//		// JSONArray js = JSONArray.fromObject(list);
//		// JSONObject obj = new JSONObject();
//		// obj.put("VERION_LIST", js.toString());
//		// response.setContentType(CommonFinalInfo.getJsonContentType());
//		// response.getWriter().print(obj);
//
//		return textbookList;
//	}

	// @ResponseBody
	// @RequestMapping("/admin/lessonCondition.prime")
	// public List<String> adminConditionLesson(@RequestBody String grade,
	// @RequestBody String textbook) throws Exception {
	// Map<String, Object> map = new HashMap<String, Object>();
	// map.put("grade", grade);
	// map.put("textbook", textbook);
	//
	// List<String> lessonList = adminService.textbookList(map);
	// return lessonList;
	// }
	// @RequestMapping(value="/sample/selectBoardList.do")
	// public ModelAndView selectBoardList(CommandMap commandMap) throws
	// Exception{
	//
	// List<Map<String,Object>> list =
	// sampleService.selectBoardList(commandMap.getMap());
	// mv.addObject("list", list);
	// if(list.size() > 0){
	// mv.addObject("TOTAL", list.get(0).get("TOTAL_COUNT"));
	// }
	// else{
	// mv.addObject("TOTAL", 0);
	// }
	//
	// return mv;
	// }

	// admin 단어관리페이지
	// @RequestMapping(value = "/admin/words.prime")
	// public ModelAndView adminStudy(Study study, HttpSession session) throws
	// Exception {
	// ModelAndView mav = new ModelAndView("redirect:/login.prime");
	//
	// logger.info(user.toString());
	// service.insert(user);
	// session.setAttribute("USER", user);
	// mav.addObject(user);
	// return mav;
	// }

	// @RequestMapping(value="duplicationCheck.prime",
	// method=RequestMethod.POST)
	// @ResponseBody
	// public int userExist(@RequestBody String username) throws Exception {
	//
	// logger.info(username.toString());
	// int isUserExist = service.userExist(username);
	// return isUserExist;
	// }
	
	@RequestMapping("/admin/insertExcel.prime")
	public void excelDataInsert(MultipartHttpServletRequest req) throws Exception{
		
//		Iterator<String> fileFullPath = req.getFileNames();
		
		File file = new File("C:/test.xlsx");
//				new File(fileFullPath.next());
				
		FileInputStream inputDocument = null;
		Workbook workbook = null;

		try {
			inputDocument = new FileInputStream(file);
			if (file.getName().toLowerCase().endsWith("xlsx")) { // 엑셀 파일의 확장자(버전)에 따라서 생성해야 할 Workbook 객체가 다르다.
				workbook = new XSSFWorkbook(inputDocument);
			}else{
				workbook = new HSSFWorkbook(inputDocument);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			Sheet workSheet = workbook.getSheetAt(0); // 첫번째 Sheet
			Iterator<Row> rowIterator = workSheet.iterator();
			
			int rowSize = workSheet.getLastRowNum() + 1; // 행의 총 개수 (행은 0부터 시작함)
			for(int i=1; i<rowSize; i++){ // i를 1부터 시작해야 두번째 행부터 데이터가 입력된다.
				Row row = workSheet.getRow(i);
				
				int cellLength = (int) row.getLastCellNum(); // 열의 총 개수
				
				String valueStr = ""; // 엑셀에서 뽑아낸 데이터를 담아놓을 String 변수 선언 및 초기화
				Study study= new Study(); // DB에 Insert하기 위해 valueStr 데이터를 옮겨담을 객체 (각자 DB 테이블의 데이터 타입에 맞춰서...)

				for(int j=0; j<cellLength; j++){
					Cell cell = row.getCell(j);
					
					// 셀에 있는 데이터들을 타입별로 분류해서 valueStr 변수에 담는다.
					if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK) { // CELL_TYPE_BLANK로만 체크할 경우 비어있는  셀을 놓칠 수 있다.
						System.out.println(j + "번, 빈값 들어감.");
						valueStr = "";
					}else{
						switch(cell.getCellType()){
							case Cell.CELL_TYPE_STRING :
								valueStr = cell.getStringCellValue();
								break;
							case Cell.CELL_TYPE_NUMERIC : // 날짜 형식이든 숫자 형식이든 다 CELL_TYPE_NUMERIC으로 인식함.
								if(DateUtil.isCellDateFormatted(cell)){ // 날짜 유형의 데이터일 경우,
									SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
									String formattedStr = dateFormat.format(cell.getDateCellValue());
									valueStr = formattedStr;
									break;
								}else{ // 순수하게 숫자 데이터일 경우,
									Double numericCellValue = cell.getNumericCellValue();
									if(Math.floor(numericCellValue) == numericCellValue){ // 소수점 이하를 버린 값이 원래의 값과 같다면,,
										valueStr = numericCellValue.intValue() + ""; // int형으로 소수점 이하 버리고 String으로 데이터 담는다.
									}else{
										valueStr = numericCellValue + "";
									}
									break;
								}
							case Cell.CELL_TYPE_BOOLEAN :
								valueStr = cell.getBooleanCellValue() + "";
								break;
						}
					}
					
					// 담아놨던 valueStr 데이터를 셀 순서대로 DTO에 set.
					switch (j) {
						case 0 :
							study.setGrade(valueStr); // 담아줘야할 DTO 객체의 메서드 타입에 따라 String을 형변환
							System.out.println(j + "번 Cell, " + "grade : " + valueStr);
							break;
						case 1 :
							study.setTextbook(valueStr);
							System.out.println(j + "번 Cell, " + "textbook : " + valueStr);
							break;
						case 2 :
							study.setLesson(Integer.parseInt(valueStr));
							System.out.println(j + "번 Cell, " + "lesson : " + Integer.parseInt(valueStr));
							break;
						case 3 :
							study.setWord(valueStr);
							System.out.println(j + "번 Cell, " + "word : " + valueStr);
							break;
						case 4 :
							study.setMeaning(valueStr);
							System.out.println(j + "번 Cell, " + "meaning : " + valueStr);
							break;
					} // switch end

				} // for loop(j) end (Cells)
				
				adminService.wordInsert(study); // Data insert.

				System.out.println(i+"번 행 Instert 완료---------------------------------------------------");
			} // for loop(i) end (Rows)
			
			inputDocument.close();		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
