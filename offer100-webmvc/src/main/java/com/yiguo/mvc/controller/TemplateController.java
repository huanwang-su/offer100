package com.yiguo.mvc.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.yiguo.bean.User;

/**
 * 控制器模版
 *
 * @author wanghuan
 * @date 2018-01-07
 */
@RequestMapping("/templete")
@Controller
public class TemplateController {

	private static Logger logger = LogManager.getLogger(TemplateController.class);

	/**
	 * 注意在配置ResourceView的时候以/开头(绝对路径)。否则会在匹配以"/"结尾的时候会包含"/templete/...", 即采用相对路径
	 * {@link javax.servlet.ServletRequest.http.HttpServletRequest#getRequestDispatcher}
	 * 
	 * @param mv
	 * @return
	 */
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String templetePage() {
		return "/templete";
	}

	/**
	 * uri传参
	 * 
	 * @param id
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/usingUri/{id}/{name}", method = RequestMethod.GET)
	public String usingUri(@PathVariable() String id, @PathVariable("name") String username) {
		System.out.println(id + " " + username);
		return "/templete";
	}

	/**
	 * uri参数传参
	 * 
	 * @param id
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/usingUriParam", method = RequestMethod.GET)
	public String usingUriParam(@RequestParam String id, @RequestParam("name") String username) {
		System.out.println(id + " " + username);
		return "/templete";
	}

	/**
	 * xxx-form表单传参，同parameter
	 * 
	 * @param id
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/usingForm", method = RequestMethod.GET)
	public String usingUriXXXForm(@RequestParam String id, @RequestParam("name") String username) {
		System.out.println(id + " " + username);
		return "/templete";
	}

	/**
	 * json body传参
	 * 
	 * @param id
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/usingBody", method = RequestMethod.GET)
	@ResponseBody
	public User usingBody(@RequestBody User user) {
		return user;
	}

	/**
	 * 传所有的可用参数
	 * 
	 * @param request
	 * @param response
	 * @param mv
	 * @param m
	 * @param v
	 * @return
	 */
	@RequestMapping(value = "/useAllAvailable", method = RequestMethod.GET)
	public String useAllAvailable(HttpServletRequest request, HttpServletResponse response, ModelAndView mv, Model m) {
		System.out.println(request.getRemoteAddr());
		System.out.println(response.getLocale());
		System.out.println(mv.getModel().equals(m));
		return "/templete";
	}

	/**
	 * 对象绑定, 注意这里会自动对localDate做转化，如果是直接传入LocalDate对象则需要加 @RequestParam
	 */
	@RequestMapping(value = "/objectBind", method = RequestMethod.GET)
	public String usingUriXXXForm(User user) {
		System.out.println(user);
		return "/templete";
	}

	/**
	 * 文件上传实例
	 * 
	 * @param request
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	public String doUploadFile(HttpServletRequest request, @RequestParam("file") MultipartFile file) {

		if (!file.isEmpty()) {
			try {
				String fileLocation = request.getServletContext().getRealPath("/") + "static/file/"
						+ System.currentTimeMillis() + file.getOriginalFilename();
				logger.info(" uploadfile on {} ${} ${0} ${1}", fileLocation);
				FileUtils.copyInputStreamToFile(file.getInputStream(), new File(fileLocation));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "/templete";

	}

	@RequestMapping(value = "/fileDownload", method = RequestMethod.GET)
	public ResponseEntity<byte[]> download(HttpServletRequest request, String type, String filename)
			throws IOException {
		HttpHeaders headers = new HttpHeaders();
		String location;
		if (StringUtils.isBlank(type)) {
			location = request.getServletContext().getRealPath("/") + "static/file/" + filename;
		} else {
			location = request.getServletContext().getRealPath("/") + "static/file/" + type + "/" + filename;
		}
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", URLEncoder.encode(filename, "UTF-8"));
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File(location)), headers,
				HttpStatus.CREATED);
	}

}
