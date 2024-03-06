/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.lecture;

import controller.AuthenticationServlet;
import database.LessionDAO;
import database.StatusDAO;
import database.StudentDAO;
import database.inGroupDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Lession;
import model.Student;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

import model.Status;
import model.User;
import units.Email;

;

/**
 *
 * @author trung
 */
public class TakeAttendance extends AuthenticationServlet {

    private void checkAttendance(Student student, Lession lession) {
        StatusDAO statusDAO = new StatusDAO();
        int absent = statusDAO.selectCountStatusAbsent(student.getId());
        if (absent == 3 || absent == 4) {
            String title = "[DVSV] Tình trạng điểm danh học kỳ "+lession.getGroup().getSemester().getNameSemester()+":"+lession.getGroup().getCourse().getCodeCourse()+"\n";
            String string = "Thân gửi " + student.getName() + ",\n"
                    + "\n"
                    + "(Nếu em đã được duyệt đơn miễn điểm danh, vui lòng bỏ qua email này).\n"
                    + "\n"
                    + "Phòng Dịch vụ sinh viên thông báo đến em về tình trạng điểm danh của em như sau: Môn "+lession.getGroup().getCourse().getCodeCourse()+": "+absent*100/20+"% absent - Vắng mặt/Tổng số: "+absent+"/20 slot\n"
                    + "\n"
                    + "Em lưu ý nên đi học chuyên cần trong thời gian tới để đảm bảo điều kiện dự thi cuối kỳ (tham gia học tập tối thiểu 80%). Theo quy định, Nhà trường sẽ không điểm danh bù cho sinh viên vì bất kỳ lý do gì nếu sinh viên không tham gia buổi học bao gồm cả những buổi học bị bỏ lỡ do xếp lớp muộn. Đồng thời em cũng đừng quên kiểm tra điểm danh hàng ngày để kịp thời xử lý khi nhận thấy có sự sai sót về điểm danh nhé. Trong trường hợp nhận thấy có sai sót về điểm danh em vui lòng làm theo hướng dẫn sau:\n"
                    + "\n"
                    + "- Với các môn học LUK: làm đơn khiếu nại điểm danh trên FAP trong vòng 2 ngày kể từ ngày diễn ra buổi học, cách thức như sau: Đăng nhập fap.fpt.edu.vn => Thủ tục/Đơn từ => Gửi đơn => Chọn: Đơn đề nghị kiểm tra trạng thái điểm danh LUK: Trình bày chi tiết thông tin buổi học bị điểm danh nhầm và các minh chứng (nếu có) => Send. Sau khi gửi đơn, sinh viên sẽ theo dõi tại mục \"Xem đơn\" để nhận phản hồi của bộ phận xử lý trong vòng 2 ngày làm việc.\n"
                    + "\n"
                    + "- Với các môn học không bao gồm LUK: Báo ngay với giảng viên trước 24h trong ngày diễn ra buổi học. Trường hợp đã báo cáo đúng thời hạn nhưng không nhận được phản hồi, sinh viên có thể làm đơn khiếu nại điểm danh, cách thức như sau: Đăng nhập fap.fpt.edu.vn => Thủ tục/Đơn từ => Gửi đơn => Chọn: Đơn khiếu nại điểm danh: Trình bày nội dung => Send. Sau khi gửi đơn, sinh viên sẽ theo dõi tại mục \"Xem đơn\" để nhận phản hồi của bộ phận xử lý trong vòng 2 ngày làm việc.\n"
                    + "\n"
                    + "Lưu ý: Đây là email gửi tự động từ hệ thống theo dõi điểm danh, nếu có bất kỳ thắc mắc nào vui lòng gửi email tới dichvusinhvien@fe.edu.vn\n"
                    + "\n"
                    + "Chúc em đạt kết quả tốt trong học kỳ "+lession.getGroup().getSemester().getNameSemester()+"!\n"
                    + "\n"
                    + "Thân mến,\n"
                    + "\n"
                    + "PHÒNG DỊCH VỤ SINH VIÊN.\n"
                    + "\n"
                    + "Lưu ý: Đây là email được gửi tự động";
            Email email = new Email();
            email.sendEmail(title,string, "trungnthe173337@fpt.edu.vn");
        } else {
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        String[] ids = req.getParameterValues("id");
        int idLession = Integer.parseInt(req.getParameter("idLession"));
        LessionDAO lessionDAO = new LessionDAO();
        lessionDAO.updateLession(idLession, 1);
        StatusDAO statusDAO = new StatusDAO();
        String reTake = req.getParameter("reTake");
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        if (reTake.isEmpty()) {
            int count = statusDAO.selectCountStatus();
            for (String id : ids) {
                count += 1;
                int status = Integer.parseInt(req.getParameter(id));
                int idStatus = count;
                statusDAO.insertStatusByIdStudentAndIdLession(idStatus, id, timestamp, idLession, status);
                StudentDAO studentDAO = new StudentDAO();
                if(status==-1){
                    checkAttendance(studentDAO.selectStudent(id), lessionDAO.selectLessionById(idLession));
                }
            }
        } else {
            for (String id : ids) {
                int status = Integer.parseInt(req.getParameter(id));
                statusDAO.updateStatusByIdStudentAndIdLessionReTake(id, timestamp, idLession, status);
                StudentDAO studentDAO = new StudentDAO();
                if(status==-1){
                    checkAttendance(studentDAO.selectStudent(id), lessionDAO.selectLessionById(idLession));
                }
            }
        }
        LocalDate currentDate = LocalDate.now();

        LocalDate monday = currentDate.with(DayOfWeek.MONDAY);
        String month = "";
        if (monday.getMonthValue() <= 9) {
            month = "0" + monday.getMonthValue();
        } else {
            month = monday.getMonthValue() + "";
        }
        //req.getRequestDispatcher("schedule?year=2024&week=05%2F02").forward(req, resp);
        String redirectUrl = "schedule?year=" + monday.getYear() + "&week=" + monday.getDayOfMonth() + "%2F" + month;
        resp.sendRedirect(redirectUrl);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        String id = req.getParameter("id");
        int idLession = Integer.parseInt(id);
        inGroupDAO inGroupDAO = new inGroupDAO();
        LessionDAO lessionDAO = new LessionDAO();
        String reTake = req.getParameter("reTake");
        Lession lession = lessionDAO.selectLessionById(idLession);
        if (reTake != null) {
            lession.setStatus(0);
        }
        StatusDAO statusDAO = new StatusDAO();
        ArrayList<Status> status = statusDAO.selectByIdLession(idLession);
        req.setAttribute("status", status);
        req.setAttribute("lession", lession);
        req.setAttribute("reTake", reTake);

        req.getRequestDispatcher("../LecturersView/attendance.jsp").forward(req, resp);
    }

}
