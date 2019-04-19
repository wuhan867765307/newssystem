package cn.news.servlet;

import cn.news.entity.Comment;
import cn.news.entity.News;
import cn.news.entity.Topic;
import cn.news.service.CommentsService;
import cn.news.service.NewsService;
import cn.news.service.TopicsService;
import cn.news.service.impl.CommentsServiceImpl;
import cn.news.service.impl.NewsServiceImpl;
import cn.news.service.impl.TopicsServiceImpl;
import cn.news.util.Page;
import com.alibaba.fastjson.JSON;
import com.mysql.jdbc.StringUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Servlet implementation class NewsServlet
 */
public class NewsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private NewsService newsService = new NewsServiceImpl();
    private TopicsService topicService = new TopicsServiceImpl();
    private CommentsService commentsService = new CommentsServiceImpl();

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        String path = request.getContextPath();
        PrintWriter out = response.getWriter();
        String method = request.getParameter("method");

        if ("getPageNews".equals(method)) {//分页查询
            String currentPageNo = request.getParameter("currentPageNo");
            String pageSize = request.getParameter("pageSize");
            int count = -1;
            try {
                count = newsService.findNewsCount();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            Page<News> pageObj = new Page<News>();
            pageObj.setPageSize(Integer.parseInt(pageSize));
            pageObj.setTotalCount(count);
            if (StringUtils.isNullOrEmpty(currentPageNo)) {
                currentPageNo = "1";
            }
            //对删除之后的总页数进行判断（有可能删除新闻之后总页数会发生变化）
            if (Integer.parseInt(currentPageNo) > pageObj.getTotalPageCount()) {
                currentPageNo = pageObj.getTotalPageCount() + "";
            }
            pageObj.setCurrentPageNo(Integer.parseInt(currentPageNo));

            String pageNews = null;
            try {
                newsService.findPageNews(pageObj);
                pageNews = JSON.toJSONString(pageObj, true);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                out.write(pageNews);
            }
        } else if ("delete".equals(method)) {//删除新闻
            String nid = request.getParameter("nid");
            String msg = "";//向前台相应的数据
            String flag = "false";//向前台状态
            if (StringUtils.isNullOrEmpty(nid)) {
                msg = "该新闻编号有误！";
            } else {
                try {
                    int result = newsService.deleteNews(Integer.parseInt(nid));
                    if (result > 0) {
                        msg = "删除成功！";
                        flag = "true";
                    } else {
                        msg = "删除失败！";
                    }
                } catch (Exception e) {
                    msg = "发生异常！";
                    e.printStackTrace();
                }
            }
            out.write("{\"flag\":\"" + flag + "\",\"msg\":\"" + msg + "\"}");
        } else if ("getNewsByNid".equals(method)) {//根据nid获取新闻信息
            String nid = request.getParameter("nid");
            News news = null;
            List<Topic> topicList = null;
            List<Comment> comments = null;
            try {
                //获取所有主题
                topicList = topicService.findAllTopics();
                //获取当前nid对应的新闻
                news = newsService.findNewsByNid(Integer.parseInt(nid));
                comments = commentsService.findCommentsByNid(Integer.parseInt(nid));
                if (comments != null && comments.size() > 0) {
                    news.setComments(comments);
                }
                request.setAttribute("topicList", topicList);
                request.setAttribute("news", news);
                request.getRequestDispatcher("/jsp/newspages/news_modify.jsp").forward(request, response);//转发到
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if("showNewsByNid".equals(method)){
            String nid = request.getParameter("nid");
            News news = null;
            List<Topic> topicList = null;
            List<Comment> comments = null;
            try {
                //获取所有主题
                topicList = topicService.findAllTopics();
                //获取当前nid对应的新闻
                news = newsService.findNewsByNid(Integer.parseInt(nid));
                comments = commentsService.findCommentsByNid(Integer.parseInt(nid));
                if (comments != null && comments.size() > 0) {
                    news.setComments(comments);
                }
                request.setAttribute("topicList", topicList);
                request.setAttribute("news", news);
                request.getRequestDispatcher("/jsp/newspages/news_read.jsp").forward(request, response);//转发到
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("newsadd".equals(method)) {
            News news = getUploadInfo(request);//获取表单信息
            try {
                addNews(news, path, out);//添加新闻
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("newsmodify".equals(method)) {
            News news = getUploadInfo(request);//获取表单信息
            try {
                modifyNews(news, path, out);//添加新闻
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        out.flush();
        out.close();

    }

    /**
     * 获取表单信息
     *
     * @param request
     */
    public News getUploadInfo(HttpServletRequest request) {
        News news = null;
        String uploadFileName = ""; //上传的文件名
        String fieldName = "";  //表单字段元素的name属性值
        //请求信息中的内容是否是multipart类型
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        //上传文件的存储路径（服务器文件系统上的绝对文件路径）
        String uploadFilePath = request.getSession().getServletContext().getRealPath("/upload/");
        File file = new File(uploadFilePath);
        if (!file.exists()) {
            file.mkdir();
        }
        String originPicPath = null;//记录原始图片名称
        boolean isFileUploadNull = false;//判断是否是选中了新的文件
        if (isMultipart) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
                //解析form表单中所有文件
                List<FileItem> items = upload.parseRequest(request);
                Iterator<FileItem> iter = items.iterator();
                news = new News();
                while (iter.hasNext()) {   //依次处理每个文件
                    FileItem item = iter.next();
                    if (item.isFormField()) {  //普通表单字段
                        fieldName = item.getFieldName();   //表单字段的name属性值
                        switch (fieldName) {
                            case "nid":
                                news.setNid(Integer.parseInt(item.getString("UTF-8")));
                                break;
                            case "ntitle":
                                news.setNtitle(item.getString("UTF-8"));
                                break;
                            case "nauthor":
                                news.setNauthor(item.getString("UTF-8"));
                                break;
                            case "nsummary":
                                news.setNsummary(item.getString("UTF-8"));
                                break;
                            case "ncontent":
                                news.setNcontent(item.getString("UTF-8"));
                                break;
                            case "ntid":
                                news.setNtid(Integer.parseInt(item.getString("UTF-8")));
                                break;
                            //原始的文件路径
                            case "hiddenPicPath":
                                originPicPath = item.getString("UTF-8");
                                break;
                        }
                    } else {  //文件表单字段
                        String fileName = item.getName();
                        if (fileName != null && !fileName.equals("")) {//如果选择了新图片就就使用新的图片名称
                            File fullFile = new File(item.getName());
                            File saveFile = new File(uploadFilePath, fullFile.getName());
                            item.write(saveFile);
                            uploadFileName = fullFile.getName();
                            news.setNpicpath(uploadFileName);
                        } else {//如果没有选择新图片就使用旧的图片名称
                            isFileUploadNull = true;
                        }
                    }
                    news.setNmodifydate(new Date());
                }
                if (isFileUploadNull == true) {
                    news.setNpicpath(originPicPath);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return news;
    }

    /**
     * 添加
     *
     * @param news
     * @param path
     * @param out
     * @throws Exception
     */
    public void addNews(News news, String path, PrintWriter out) throws Exception {
        //添加新闻到数据库中
        int result = newsService.addNews(news);
        if (result > 0) {
            //添加成功！
            out.print("<script>alert('添加成功！')</script>");
            out.print("<script>window.location='" + path + "/jsp/newspages/admin.jsp'</script>");
        } else {
            //添加失败
            out.print("<script>alert('添加失败！')</script>");
            out.print("<script>window.location='" + path + "/jsp/newspages/news_add.jsp'</script>");
        }
    }

    /**
     * 修改
     *
     * @param news
     * @param path
     * @param out
     * @throws Exception
     */
    public void modifyNews(News news, String path, PrintWriter out) throws Exception {
        //修改新闻到数据库中
        int result = newsService.modifyNews(news);
        if (result > 0) {
            //修改成功！
            out.print("<script>alert('修改成功！')</script>");
            out.print("<script>window.location='" + path + "/jsp/newspages/admin.jsp'</script>");
        } else {
            //修改失败
            out.print("<script>alert('修改失败！')</script>");
            out.print("<script>window.location='" + path + "/jsp/newspages/news_modify.jsp'</script>");
        }
    }

}
