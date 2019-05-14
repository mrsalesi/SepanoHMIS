/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HMIS;

import cms.access.Access_User;
import cms.tools.Js;
import cms.tools.Server;
import cms.tools.jjTools;
import cms.tools.jjValidation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.table.DefaultTableModel;
import jj.jjCalendar_IR;
import jj.jjDatabase;
import jj.jjDatabaseWeb;
import jj.jjNumber;

/**
 *
 * @author shohreh.shiran Date 1397.12.18 Session:صورت جلسه و دعوتنامه
 */
public class Approved {

    public static String tableName = "hmis_approved";
    public static String _id = "id";
    public static String _sessionsId = "approved_sessionsId";//ایدی جلسه
//    public static String _commetteId = "approved_commettesId";//ایدی کمیته
    public static String _title = "approved_title";//عنوان مصوبه
    public static String _startDate = "approved_startDate";//تاریخ شروع 
    public static String _endDate = "approved_endDate";//تاریخ پایان
    public static String _trackerId = "approved_trackerId";//مسئول پیگیری
    public static String _executorId = "approved_executorId";//مسئول اجرا
    public static String _status = "approved_status";//وضعیت
    public static String _statusLog = "approved_statusLog";//روند وضعیت
    public static String _description = "approved_description";//توضیحات
    public static String _file = "approved_file";//مستندات از طرف دبیر کمیته
    public static String _fileOfResponsible = "approved_fileOfResponsible";//مستندات مسئول اجرا یا مسئول پیگیری
    public static String _fileCheckOut = "approved_fileCheckOut";//بررسی مصوبات توسط دبیر کمیته

    public static int rul_rfsAll = 0;
    public static int rul_rfs = 0;
    public static int rul_ins = 0;
    public static int rul_edt = 0;
    public static int rul_dlt = 0;
    public static int rul_lng2 = 0;
    public static int rul_lng3 = 0;
    public static int rul_lng4 = 0;
    public static int rul_lng5 = 0;
    public static String lbl_insert = "ذخیره";
    public static String lbl_delete = "حذف";
    public static String lbl_edit = "ویرایش";
    public static String status_inDoing = "درحال انجام";
    public static String status_unDone = "غیر قابل انجام";
    public static String status_done = "انجام شده";

    public static String refresh(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isFromClient) throws Exception {
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_rfs);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
//            boolean accRefAll = Access_User.hasAccess(request, db, rul_rfsAll);
//            boolean accRef = Access_User.hasAccess(request, db, rul_rfs);

            StringBuilder html = new StringBuilder();
            html.append("<div class=\"table-wrapper\">\n");
            html.append("<table id='refreshApproved' class='table display responsive' class='tahoma10' style='direction: rtl;width:982px'><thead>");
            html.append("<th width='5%'>کد</th>");
            html.append("<th width='10%'>عنوان مصوبه</th>");
            html.append("<th width='15%'>مسئول اجرا</th>");
            html.append("<th width='20%'>مسئول پیگیری</th>");
            html.append("<th width='15%'>تاریخ شروع </th>");
            html.append("<th width='15%'>تاریخ پایان </th>");
            html.append("<th width='15%'>وضعیت</th>");
            html.append("<th width='40%'>ویرایش</th>");
            html.append("</thead><tbody>");

//            if (accRefAll) { //برای دسترسی مدیر
            DefaultTableModel dtm = db.Select(Approved.tableName);
            List<Map<String, Object>> row = jjDatabase.separateRow(dtm);
//                for (int i = 0; i < row.size(); i++) {
//                    html.append("<tr onclick='hmisApproved.m_select(" + row.get(i).get(_id) + ")' class='mousePointer'>");
//                    List<Map<String, Object>> RolesTrackerIdRow = jjDatabase.separateRow(db.Select(Role.tableName, Role._id + "=" + row.get(i).get(Approved._trackerId)));
//                    List<Map<String, Object>> UserTrackerIdRow = jjDatabase.separateRow(db.Select(Access_User.tableName, Access_User._id + "=" + RolesTrackerIdRow.get(0).get(Role._user_id)));
//                    List<Map<String, Object>> RolesExecutorIdRow = jjDatabase.separateRow(db.Select(Role.tableName, Role._id + "=" + row.get(i).get(Approved._executorId)));
//                    List<Map<String, Object>> UserExecutorIdRow = jjDatabase.separateRow(db.Select(Access_User.tableName, Access_User._id + "=" + RolesExecutorIdRow.get(0).get(Role._user_id)));
//                    html.append("<td class='c'>" + row.get(i).get(Approved._id) + "</td>");
//                    html.append("<td class='r'>" + row.get(i).get(Approved._title) + "</td>");
//                    html.append("<td class='r'>" + RolesTrackerIdRow.get(0).get(Role._title) + "-" + UserTrackerIdRow.get(0).get(Access_User._name) + " " + UserTrackerIdRow.get(0).get(Access_User._family) + "</td>");
//                    html.append("<td class='r'>" + RolesExecutorIdRow.get(0).get(Role._title) + "-" + UserExecutorIdRow.get(0).get(Access_User._name) + " " + UserExecutorIdRow.get(0).get(Access_User._family) + "</td>");
////                html3.append("<td class='r'>" + approvedRow.get(i).get(Approved._responsibleForExecutionId) + "</td>");
//                    html.append("<td class='r'>" + jjCalendar_IR.getViewFormat(row.get(i).get(Approved._startDate)) + "</td>");
//                    html.append("<td class='r'>" + jjCalendar_IR.getViewFormat(row.get(i).get(Approved._endDate)) + "</td>");
//                    html.append("<td class='r'>" + row.get(i).get(Approved._status) + "</td>");
//                    html.append("<td class='r'><i class='icon ion-gear-a' style='color:#a02311'></i></td>");
//                    html.append("</tr>");
//                }
//            }
//            if (accRef) {// برای دسترسی مسئولین اجرایی وپیگیری
//            List<Map<String, Object>> row = jjDatabase.separateRow(db.otherSelect("SELECT hmis_approved.id"
//                    + " ,hmis_sessions.id"
//                    + ",approved_trackerId,approved_executorId,approved_status,"
//                    + "approved_sessionsId,hmis_sessions.sessions_status,approved_startDate,"
//                    + "approved_endDate,approved_title "
//                    + "FROM "
//                    + "hmis_approved LEFT JOIN "
//                    + "hmis_sessions ON approved_sessionsId=hmis_sessions.id "
//                    + "WHERE sessions_status='ابلاغ شده'"
//                    + " AND  "
//                    + "approved_trackerId=" + jjTools.getSeassionUserId(request)
//                    + " OR "
//                    + "sessions_status='ابلاغ شده'"
//                    + " AND "
//                    + " approved_executorId=" + jjTools.getSeassionUserId(request) + ""));
//            List<Map<String, Object>> row = jjDatabase.separateRow(db.JoinLeft(Approved.tableName, Sessions.tableName, "hmis_approved.id"
//                    + ","
//                    + Sessions._status
//                    + ","
//                    + "hmis_sessions.id"
//                    + ","
//                    + _trackerId
//                    + ","
//                    + Approved._executorId
//                    + ","
//                    + Approved._title
//                    + ","
//                    + Approved._status
//                    + ","
//                    + Approved._endDate
//                    + ","
//                    + Approved._startDate
//                    + ","
//                    + Approved._sessionsId,
//                    Approved._sessionsId, Sessions._id,
//                    " WHERE " + Sessions._status + "='" + Sessions.status_communicated + "' "
//                    + "AND " + _executorId + "=" + jjTools.getSeassionUserId(request) + " "
//                    + "OR " + Sessions._status + "='" + Sessions.status_communicated + "'"
//                    + " AND " + _trackerId + "=" + jjTools.getSeassionUserId(request) + ""));
            for (int i = 0; i < row.size(); i++) {
//                List<Map<String ,Object>> SessiondsRow=jjDatabase.separateRow(db.Select(Sessions.tableName,Sessions._id+"="+row.get(i).get(_sessionsId)+" WHERE hmis_sessions.sessions_status='ابلاغ شده' AND  "+row.get(i).get(_executorId)+"="+jjTools.getSeassionUserId(request)+" OR hmis_sessions.sessions_status='ابلاغ شده' AND "+row.get(i).get(_trackerId)+"="+jjTools.getSeassionUserId(request)+"" ));
                html.append("<tr onclick='hmisApproved.m_select(" + row.get(i).get(_id) + ")' class='mousePointer'>");
                List<Map<String, Object>> RolesTrackerIdRow = jjDatabase.separateRow(db.Select(Role.tableName, Role._id + "=" + row.get(i).get(Approved._trackerId)));
                List<Map<String, Object>> UserTrackerIdRow = jjDatabase.separateRow(db.Select(Access_User.tableName, Access_User._id + "=" + RolesTrackerIdRow.get(0).get(Role._user_id)));
                List<Map<String, Object>> RolesExecutorIdRow = jjDatabase.separateRow(db.Select(Role.tableName, Role._id + "=" + row.get(i).get(Approved._executorId)));
                List<Map<String, Object>> UserExecutorIdRow = jjDatabase.separateRow(db.Select(Access_User.tableName, Access_User._id + "=" + RolesExecutorIdRow.get(0).get(Role._user_id)));
                html.append("<td class='c'>" + row.get(i).get(_id) + "</td>");
                System.out.println("" + row.get(i).get(_id));
                html.append("<td class='r'>" + row.get(i).get(_title) + "</td>");
                html.append("<td class='r'>" + RolesTrackerIdRow.get(0).get(Role._title) + "-" + UserTrackerIdRow.get(0).get(Access_User._name) + " " + UserTrackerIdRow.get(0).get(Access_User._family) + "</td>");
                html.append("<td class='r'>" + RolesExecutorIdRow.get(0).get(Role._title) + "-" + UserExecutorIdRow.get(0).get(Access_User._name) + " " + UserExecutorIdRow.get(0).get(Access_User._family) + "</td>");
//                html3.append("<td class='r'>" + approvedRow.get(i).get(Approved._responsibleForExecutionId) + "</td>");
                html.append("<td class='r'>" + jjCalendar_IR.getViewFormat(row.get(i).get(_startDate)) + "</td>");
                html.append("<td class='r'>" + jjCalendar_IR.getViewFormat(row.get(i).get(_endDate)) + "</td>");
                html.append("<td class='r'>" + row.get(i).get(_status) + "</td>");
                html.append("<td class='r'><i class='icon ion-gear-a' style='color:#a02311'></i></td>");
                html.append("</tr>");
            }
//            }
            html.append("</tbody></table>");
//            html.append("</div>");
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "400";
            }
            if (panel.equals("")) {
                panel = "swApprovedTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshApproved", "300", 0, "", "جلسات");
            Server.outPrinter(request, response, html2);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * creator shohre shiran 13971218
     *
     * @param request
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String insert(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_ins);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            String sessionsId = jjTools.getParameter(request, "hmis_sessions_id");
            StringBuilder html = new StringBuilder();
            jjCalendar_IR ir = new jjCalendar_IR();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_endDate, jjTools.getParameter(request, _endDate).replaceAll("/", ""));
            map.put(_startDate, jjTools.getParameter(request, _startDate).replaceAll("/", ""));
            map.put(_file, jjTools.getParameter(request, _file));
            map.put(_description, jjTools.getParameter(request, _description));
            map.put(_executorId, jjTools.getParameter(request, _executorId));
            map.put(_trackerId, jjTools.getParameter(request, _trackerId));
            map.put(_status, jjTools.getParameter(request, _status));
            map.put(_sessionsId, sessionsId);
            map.put(_statusLog,
                    jjTools.getParameter(request, _status)
                    + "-"
                    + jjCalendar_IR.getViewFormat(jjCalendar_IR.getDatabaseFormat_8length("", true))
                    + " "
                    + new jjCalendar_IR().getTimeFormat_8length()
                    + "#A#"
            );
            List<Map<String, Object>> sessionsRow = jjDatabase.separateRow(db.Select(Sessions.tableName, Sessions._id + "=" + sessionsId));
//            if (!sessionsRow.get(0).get(Sessions._status).equals(Sessions.status_communicated)) {
            DefaultTableModel dtm = db.insert(tableName, map);
            if (dtm.getRowCount() == 0) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                Server.outPrinter(request, response, Js.dialog(errorMessage));
                return "";
            }
//            } else {
//
//                Server.outPrinter(request, response, Js.modal("کاربر گرامی ثبت مصوبه جدید امکان پذیر نیست", "پیام سامانه"));
//                return "";
//
//            }

            Server.outPrinter(request, response, Js.jjSessions.select(sessionsId));
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * creator shohre shiran 13971218
     *
     * @param request
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String add_new(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            StringBuffer html = new StringBuffer();

            boolean accIns = Access_User.hasAccess(request, db, rul_ins);
            if (accIns) {
                html.append(Js.setHtml("#ApprovedInSessions_button", "<button  class=\"btn btn-outline-success  btn-block mg-b-10\" id=\"insert_Approved_new\" onclick='" + Js.jjApproved.insert() + "'>" + lbl_insert + "</button>"));
//                html.append(Js.buttonMouseClick("#insert_Approved_new", Js.jjApproved.insert()));
            }
            Server.outPrinter(request, response, html.toString());
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * creator shohre shiran 13971218
     *
     * @param request
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String select(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            System.out.println("id=" + id);
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
//            List<Map<String, Object>> commettesRow = jjDatabase.separateRow(db.Select(Commettes.tableName,Commettes._id + "=" +row.get(0).get(_commetteId)));

            if (row.size() == 0) {
                String errorMessage = "رکوردی با این کد وجود ندارد.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Select Fail;";
                }
                return Js.dialog(errorMessage);
            }
            StringBuilder html = new StringBuilder();
            StringBuilder html2 = new StringBuilder();
            StringBuilder html3 = new StringBuilder();
            List<Map<String, Object>> RolesTrackerIdRow = jjDatabase.separateRow(db.Select(Role.tableName, Role._id + "=" + row.get(0).get(Approved._trackerId)));
            List<Map<String, Object>> UserTrackerIdRow = jjDatabase.separateRow(db.Select(Access_User.tableName, Access_User._id + "=" + RolesTrackerIdRow.get(0).get(Role._user_id)));
            List<Map<String, Object>> RolesExecutorIdRow = jjDatabase.separateRow(db.Select(Role.tableName, Role._id + "=" + row.get(0).get(Approved._executorId)));
            List<Map<String, Object>> UserExecutorIdRow = jjDatabase.separateRow(db.Select(Access_User.tableName, Access_User._id + "=" + RolesExecutorIdRow.get(0).get(Role._user_id)));
            html.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));
            html.append(Js.setVal("#" + _title, row.get(0).get(_title)));
            html.append(Js.setVal("#" + _status, row.get(0).get(_status)));
            html.append(Js.setVal("#" + _description, row.get(0).get(_description)));
            html.append(Js.setVal("#trackerId", RolesTrackerIdRow.get(0).get(Role._title) + "-" + UserTrackerIdRow.get(0).get(Access_User._name) + " " + UserTrackerIdRow.get(0).get(Access_User._family)));
            html.append(Js.setVal("#executorId", RolesExecutorIdRow.get(0).get(Role._title) + "-" + UserExecutorIdRow.get(0).get(Access_User._name) + " " + UserExecutorIdRow.get(0).get(Access_User._family)));
            html.append(Js.setVal("#endDate", jjCalendar_IR.getViewFormat(row.get(0).get(_endDate))));
            html.append(Js.setVal("#startDate", jjCalendar_IR.getViewFormat(row.get(0).get(_startDate))));
            if (!row.get(0).get(_file).toString().equals("")) {
                String[] File = (row.get(0).get(_file).toString().replaceAll("#A#", "%23A%23")).split("%23A%23");
                for (int i = 0; i < File.length; i++) {
                    html3.append(" <div class='col-lg-2'><input value='" + File[i] + "' class='form-control is-valid hasDatepicker' /></div>");
                    html3.append("  <div class=\"col-lg-2\" ><a id='downloadFile_Approved'   href='upload/" + File[i] + "' class='btn btn-outline-success  btn-block mg-b-10'>دانلود فایل</a></div>");
                }
            }
            boolean accEdt = Access_User.hasAccess(request, db, rul_edt);//
            boolean accDel = Access_User.hasAccess(request, db, rul_dlt);//
            html2.append("<div class='row'>");
            if (accEdt) {
                html2.append("<div class=\"col-lg-12\">");
                html2.append("<button  id='edit_Approved' class='btn btn-outline-warning btn-block mg-b-10' onclick='" + Js.jjApproved.edit() + "' >" + lbl_edit + "</button>");
                html2.append("</div>");
            }
//            if (accDel) {
//                html2.append("<div class=\"col-lg-6\">");
////                List<Map<String, Object>> sessionsRow = jjDatabase.separateRow(db.Select(Sessions.tableName, Sessions._id + "=" + row.get(0).get(_sessionsId)));
////                if (sessionsRow.get(0).get(Sessions._status).equals(Sessions.status_created)) {
//                html2.append("<button id='delete_Approved'  class='btn btn-outline-danger btn-block mg-b-10' onclick='" + Js.jjApproved.delete(id) + "'>" + lbl_delete + "</button>");
////                }
////                html.append(Js.buttonMouseClick("#delete_Approved", ));
//                html2.append("</div>");
//            }
            html2.append("</div>");
            String script = Js.setHtml("Approved_button", html2);
            script += html.toString();
            script += Js.setHtml("#inputTextSelectorDiv", html3);
            Server.outPrinter(request, response, script);
            return "";

        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * انتخاب مصوبات و سلکت آن ها در جلسات
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String selectInSessions(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            System.out.println("id=" + id);
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
//            List<Map<String, Object>> commettesRow = jjDatabase.separateRow(db.Select(Commettes.tableName,Commettes._id + "=" +row.get(0).get(_commetteId)));

            if (row.size() == 0) {
                String errorMessage = "رکوردی با این کد وجود ندارد.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Select Fail;";
                }
                return Js.dialog(errorMessage);
            }
            StringBuilder html = new StringBuilder();
            StringBuilder html2 = new StringBuilder();
            StringBuilder html3 = new StringBuilder();

            html.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));
            html.append(Js.setVal("#" + _title, row.get(0).get(_title)));
            html.append(Js.setVal("#" + _status, row.get(0).get(_status)));
            System.out.println("********************" + row.get(0).get(_statusLog));
            html.append(Js.setVal("#" + _description, row.get(0).get(_description)));
            html.append(Js.setVal("#" + _trackerId, row.get(0).get(_trackerId)));
            html.append(Js.setVal("#" + _executorId, row.get(0).get(_executorId)));
            html.append(Js.setVal("#" + _endDate, jjCalendar_IR.getViewFormat(row.get(0).get(_endDate))));
            html.append(Js.setVal("#" + _startDate, jjCalendar_IR.getViewFormat(row.get(0).get(_startDate))));
            if (!row.get(0).get(_file).toString().equals("")) {
                String[] File = (row.get(0).get(_file).toString().replaceAll("#A#", "%23A%23")).split("%23A%23");
                for (int i = 0; i < File.length; i++) {
                    html3.append("<input class='col-xs-12' value='" + File[i] + "' >");
                }
            }
            boolean accEdt = Access_User.hasAccess(request, db, rul_edt);//
            boolean accDel = Access_User.hasAccess(request, db, rul_dlt);//
            html2.append("<div class='row'>");
            if (accEdt) {
                html2.append("<div class=\"col-lg-6\">");
                List<Map<String, Object>> sessionsRow = jjDatabase.separateRow(db.Select(Sessions.tableName, Sessions._id + "=" + row.get(0).get(_sessionsId)));
                if (sessionsRow.get(0).get(Sessions._status).equals(Sessions.status_created)) {
                    html2.append("<button  id='edit_ApprovedInSessions' class='btn btn-outline-warning btn-block mg-b-10' onclick='" + Js.jjApproved.edit() + "' >" + lbl_edit + "</button>");
                }
//                html.append(Js.buttonMouseClick("#edit_Approved", ));   
                html2.append("</div>");
            }
            if (accDel) {
                html2.append("<div class=\"col-lg-6\">");
                List<Map<String, Object>> sessionsRow = jjDatabase.separateRow(db.Select(Sessions.tableName, Sessions._id + "=" + row.get(0).get(_sessionsId)));
                if (sessionsRow.get(0).get(Sessions._status).equals(Sessions.status_created)) {
                    html2.append("<button id='delete_ApprovedInSessions'  class='btn btn-outline-danger btn-block mg-b-10' onclick='hmisApproved.editInSessions();'>" + lbl_delete + "</button>");
                }
//                html.append(Js.buttonMouseClick("#delete_Approved", ));
                html2.append("</div>");
            }
            html2.append("</div>");
            String script = Js.setHtml("ApprovedInSessions_button", html2);
            script += html.toString();
            script += Js.setHtml("#inputTextSelectorDiv", html3);
            Server.outPrinter(request, response, script);
            return "";

        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * انتخاب مصوبات در قسمت جلسات برای بررسی مصوبات قبلی این کمیته
     *
     * @param request
     * @param response
     * @param db
     * @param needString
     * @return
     * @throws Exception
     */
    public static String selectApprovedPrevious(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            System.out.println("id=" + id);
            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(tableName, _id + "=" + id));
//            List<Map<String, Object>> commettesRow = jjDatabase.separateRow(db.Select(Commettes.tableName,Commettes._id + "=" +row.get(0).get(_commetteId)));

            if (row.size() == 0) {
                String errorMessage = "رکوردی با این کد وجود ندارد.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Select Fail;";
                }
                return Js.dialog(errorMessage);
            }
            StringBuilder html = new StringBuilder();
            StringBuilder html2 = new StringBuilder();
            StringBuilder html3 = new StringBuilder();
            List<Map<String, Object>> RolesTrackerIdRow = jjDatabase.separateRow(db.Select(Role.tableName, Role._user_id + "," + Role._id + "," + Role._title, Role._id + "=" + row.get(0).get(Approved._trackerId)));
            List<Map<String, Object>> UserTrackerIdRow = jjDatabase.separateRow(db.Select(Access_User.tableName, Access_User._name + "," + Access_User._family, Access_User._id + "=" + RolesTrackerIdRow.get(0).get(Role._user_id)));
            List<Map<String, Object>> RolesExecutorIdRow = jjDatabase.separateRow(db.Select(Role.tableName, Role._user_id + "," + Role._id + "," + Role._title, Role._id + "=" + row.get(0).get(Approved._executorId)));
            List<Map<String, Object>> UserExecutorIdRow = jjDatabase.separateRow(db.Select(Access_User.tableName, Access_User._name + "," + Access_User._family, Access_User._id + "=" + RolesExecutorIdRow.get(0).get(Role._user_id)));
            html.append(Js.setVal("#approvedPrevious_id", row.get(0).get(_id)));
            html.append(Js.setVal("#approvedPrevious_title", row.get(0).get(_title)));
            html.append(Js.setVal("#approvedPrevious_status", row.get(0).get(_status)));
//            System.out.println("********************" + row.get(0).get(_statusLog));
            html.append(Js.setVal("#approvedPrevious_description", row.get(0).get(_description)));
            html.append(Js.setVal("#approvedPrevious_trackerId", RolesTrackerIdRow.get(0).get(Role._title) + "-" + UserTrackerIdRow.get(0).get(Access_User._name) + " " + UserTrackerIdRow.get(0).get(Access_User._family)));
            html.append(Js.setVal("#approvedPrevious_executorId", RolesExecutorIdRow.get(0).get(Role._title) + "-" + UserExecutorIdRow.get(0).get(Access_User._name) + " " + UserExecutorIdRow.get(0).get(Access_User._family)));
            html.append(Js.setVal("#approvedPrevious_endDate", jjCalendar_IR.getViewFormat(row.get(0).get(_endDate))));
            html.append(Js.setVal("#approvedPrevious_startDate", jjCalendar_IR.getViewFormat(row.get(0).get(_startDate))));
            html.append(Js.setHtml("#approvedPrevious_statusLog", (row.get(0).get(_statusLog).toString()).replaceAll("#A#", "<br/>")));
            if (!row.get(0).get(_file).toString().equals("")) {
                String[] File = (row.get(0).get(_file).toString().replaceAll("#A#", "%23A%23")).split("%23A%23");
                for (int i = 0; i < File.length; i++) {
                    html3.append("<input class='col-xs-12' value='" + File[i] + "' >");
                }
            }
            boolean accEdt = Access_User.hasAccess(request, db, rul_edt);//
            boolean accDel = Access_User.hasAccess(request, db, rul_dlt);//
//            html2.append("<div class='row'>");
//            if (accEdt) {
//                html2.append("<div class=\"col-lg-6\">");
//                List<Map<String, Object>> sessionsRow = jjDatabase.separateRow(db.Select(Sessions.tableName, Sessions._id + "=" + row.get(0).get(_sessionsId)));
//                if (sessionsRow.get(0).get(Sessions._status).equals(Sessions.status_created)) {
//                    html2.append("<button  id='edit_ApprovedPrevious' class='btn btn-outline-warning btn-block mg-b-10' onclick='" + Js.jjApproved.edit() + "' >" + lbl_edit + "</button>");
//                }
////                html.append(Js.buttonMouseClick("#edit_Approved", ));   
//                html2.append("</div>");
//            }
            html2.append("<div class='row'>");
            if (accEdt) {
                html2.append("<div class=\"col-lg-6\">");
                html2.append("<button  id='edit_ApprovedPrevious' class='btn btn-outline-warning btn-block mg-b-10' onclick='hmisApproved.editApprovedPrevious();' >" + lbl_edit + "</button>");
                html2.append("</div>");
            }
//            if (accDel) {
//                html2.append("<div class=\"col-lg-6\">");
//                List<Map<String, Object>> sessionsRow = jjDatabase.separateRow(db.Select(Sessions.tableName, Sessions._id + "=" + row.get(0).get(_sessionsId)));
//                if (sessionsRow.get(0).get(Sessions._status).equals(Sessions.status_created)) {
//                    html2.append("<button id='delete_ApprovedPrevious'  class='btn btn-outline-danger btn-block mg-b-10' onclick='hmisApproved.editInSessions();'>" + lbl_delete + "</button>");
//                }
//                html2.append("</div>");
//            }
            html2.append("</div>");
            String script = "";
            script += Js.setHtml("ApprovedPrevious_button", html2);//دکمه های مربوط به مصوبات قبلی 
            script += html.toString();
//            script += Js.setHtml("#inputTextSelectorDiv", html2);
            Server.outPrinter(request, response, script);
            return "";

        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String changeStatus(jjDatabaseWeb db, String id, String newSatus) {
        try {
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                return Js.dialog(errorMessageId);
            }
            String oldStatus = jjDatabaseWeb.separateRow(db.Select(tableName, _status, _id + "=" + id)).get(0).get(_status).toString();

            if (!oldStatus.equals(newSatus)) {
                db.otherStatement("UPDATE " + tableName + " SET " + _statusLog
                        + "=concat(ifnull(" + _statusLog + ",''),'"
                        + newSatus
                        + "-"
                        + jjCalendar_IR.getViewFormat(new jjCalendar_IR().getDBFormat_8length())
                        + " "
                        + new jjCalendar_IR().getTimeFormat_8length()
                        + "%23A%23"
                        + "') ,"
                        + _status + "='" + newSatus + "'  WHERE id=" + id + ";");
            }
            return "";
        } catch (Exception ex) {
            Server.ErrorHandler(ex);
            return "عملیات تغییر وضعیت بدرستی صورت نگرفت. Err166";
        }
    }

    /**
     * creator shohre shiran 13971218
     *
     * @param request
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String edit(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String id = jjTools.getParameter(request, "hmis_approved_id");
            String script = "";
            String hasAccess = Access_User.getAccessDialog(request, db, rul_edt);
            if (!hasAccess.equals("")) {
                Server.outPrinter(request, response, hasAccess);
                return "";
            }

            Map<String, Object> map = new HashMap<>();
            map.put(_endDate, jjTools.getParameter(request, _endDate).replaceAll("/", ""));
            map.put(_startDate, jjTools.getParameter(request, _startDate).replaceAll("/", ""));
            map.put(_fileOfResponsible, jjTools.getParameter(request, _fileOfResponsible));
            map.put(_description, jjTools.getParameter(request, _description));
            map.put(_status, jjTools.getParameter(request, _status));

            String result = changeStatus(db, id, jjTools.getParameter(request, _status));
            if (!db.update(tableName, map, _id + "=" + id)) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                return Js.dialog(errorMessage);
            } else {
                if (result.isEmpty()) {
                    script += "hmisApproved.m_refresh();";
                    script += Js.modal("تغییرات انجام شد", "پیام سامانه");
                }
            }
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String editInSessions(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String id = jjTools.getParameter(request, "hmis_approved_id");
            String sessionsId = jjTools.getParameter(request, "hmis_sessions_id");

            String hasAccess = Access_User.getAccessDialog(request, db, rul_edt);
            if (!hasAccess.equals("")) {
                Server.outPrinter(request, response, hasAccess);
                return "";
            }

            Map<String, Object> map = new HashMap<>();
            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_endDate, jjTools.getParameter(request, _endDate).replaceAll("/", ""));
            map.put(_startDate, jjTools.getParameter(request, _startDate).replaceAll("/", ""));
            map.put(_file, jjTools.getParameter(request, _file));
            map.put(_description, jjTools.getParameter(request, _description));
            map.put(_executorId, jjTools.getParameter(request, _executorId));
            map.put(_trackerId, jjTools.getParameter(request, _trackerId));
            map.put(_status, jjTools.getParameter(request, _status));

            if (!db.update(tableName, map, _id + "=" + id)) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                return Js.dialog(errorMessage);
            }
            String script = Js.jjSessions.select(sessionsId);
            script += "$('#insertApproved2').slideUp();";
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * ویرایش مصوبات مربوط به جلسات قبلی
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String editApprovedPrevious(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String id = jjTools.getParameter(request, "approvedId");
            String sessionsId = jjTools.getParameter(request, "hmis_sessions_id");

            String hasAccess = Access_User.getAccessDialog(request, db, rul_edt);
            if (!hasAccess.equals("")) {
                Server.outPrinter(request, response, hasAccess);
                return "";
            }

            Map<String, Object> map = new HashMap<>();
//            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_endDate, jjTools.getParameter(request, _endDate).replaceAll("/", ""));
            map.put(_startDate, jjTools.getParameter(request, _startDate).replaceAll("/", ""));
//            map.put(_file, jjTools.getParameter(request, _file));
//            map.put(_description, jjTools.getParameter(request, _description));
//            map.put(_executorId, jjTools.getParameter(request, _executorId));
//            map.put(_trackerId, jjTools.getParameter(request, _trackerId));
            map.put(_status, jjTools.getParameter(request, _status));

            if (!db.update(tableName, map, _id + "=" + id)) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                return Js.dialog(errorMessage);
            }
            System.out.println("sessionsId=" + sessionsId);
            String script = Js.jjSessions.select(sessionsId);
            script += "$('#approvedPreviousDiv').slideUp();";
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    /**
     * ای دی مصوبات
     *
     * @param request
     * @param response
     * @param db
     * @param isPost
     * @return
     * @throws Exception
     */
    public static String delete(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_dlt);
            if (!hasAccess.equals("")) {
                Server.outPrinter(request, response, hasAccess);
                return "";
            }
            String sessionsId = jjTools.getParameter(request, "hmis_sessions_id");

            String id = jjTools.getParameter(request, _id);
            System.out.println("id=" + id);
//            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
//            if (!errorMessageId.equals("")) {
//                if (jjTools.isLangEn(request)) {
//                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
//                }
//                return Js.dialog(errorMessageId);
//            }
            if (!db.delete(tableName, _id + "=" + id)) {
                String errorMessage = "عملیات حذف به درستی صورت نگرفت";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Delete Fail;";
                }
                Server.outPrinter(request, response, Js.dialog(errorMessage));
                return "";
            }
            String script = Js.jjSessions.select(sessionsId);
            script += "$('#insertApproved2').slideUp();";
            Server.outPrinter(request, response, script);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String getClassCssForStatus(String satus) {
        if (satus.equals(status_inDoing)) {
            return "vaziat_sabteAvalie";
        } else if (satus.equals(status_done)) {
            return "vaziat_taeedeNahaie";
        } else if (satus.equals(status_unDone)) {
            return "vaziat_ersalShodeBeModireFani";// این کلاس در فایل های سی اس اس تعریف میشود و در قسمت های مختلف جدول نشان داده می شود
        }
        return "";
    }
}