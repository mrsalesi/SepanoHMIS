/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var hmisSessions = {
    tableName: "Sessions",
    f_id: "id",
    loadForm: function () {
        if ($("#swSessionsForm").html() == '') {
            $("#swSessionsForm").load("formHMIS/05OneSession.html", null, function () {
                new jj('#sendFilesApproved').jjAjaxFileUpload3('attachFileApproved', '#approved_file', '');
                new jj('#sendFilesSessions').jjAjaxFileUpload4('attachFileSessions', '#sessions_file', '#inputTextSelectorSessionsDiv'); //در این تابع خودمان پنل اینپوت را می فرستیم که فایل ها در آنجا نمایش داده شود 
                new jj('#sendFilesApprovedPrevious').jjAjaxFileUpload4('attachFileApprovedPrevious', '#approved_fileCheckOut', '#inputFileApprovedPreviousDiv');
                new jj("#sessions_nextSessionDate").jjCalendarWithYearSelector(1397, 1420);
                $("#cancel_Sessions").button().click(function (e) {
                    hmisSessions.m_clean();
                    hmisSessions.m_show_tbl();
                });
                hmisSessions.m_refresh();
            });
        }
    },
    m_refresh: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + hmisSessions.tableName + ".refresh";
        param += "&panel=" + (containerId == null ? "swSessionsTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? 800 : tableHeight);
        param += "&jj=1";
        new jj(param).jjAjax2(false);
    },
    m_show_form: function () {
        $('#swSessionsTbl').hide();
        $('#swSessionsForm').show();
        hmisSessions.tabSizeForm();
    },
    m_clean: function () {
        new jj("#" + hmisSessions.f_content_id).jjVal("");
        new jj("#" + hmisSessions.f_title).jjVal("");
        new jj("#" + hmisSessions.f_lang).jjVal("1");
        new jj("#" + hmisSessions.f_parent).jjVal("0");
    },
    m_add_new: function () {
        jj("do=" + hmisSessions.tableName + ".add_new&jj=1").jjAjax2(false);
        hmisSessions.m_show_form();
        hmisSessions.m_clean();
    },
    m_show_tbl: function () {
        $('#swSessionsTbl').show();
        $('#swSessionsForm').hide();
        if ($('#swSessionsTbl').html() == "") {
            hmisSessions.m_refresh();
        }
        hmisSessions.tabSizeTbl();
    },
    m_insert: function () {
//        var valid =  hmisSessions.m_validation();
//        if (valid == "") {
        var param = "";
        param += "do=" + hmisSessions.tableName + ".insert";
        param += "&" + new jj('#swSessionsForm').jjSerial();
        new jj(param).jjAjax2(false);
        hmisSessions.m_show_tbl();
        hmisSessions.m_clean();
//        } else {
//            new jj(valid).jjDialog();
//        }
    },
    m_edit: function () {
//        var valid = hmisPlan.m_validation();
//        if (valid == "") {
        var temp = $('#audience input:checkbox[class=checkboxAudience]:checked'); //مدعوین سمت دار
        var temp3 = $('#audience input:checkbox[class=checkboxAudienceOutSide]:checked'); //مدعوین خارج از سازمان
        var temp2 = "";
        var temp4 = "";
        for (var i = 0; i < temp.length; i++) {
            temp2 += $(temp[i]).attr('name') + "%23A%23"; //id user audience
//            temp2 +=$(temp[i]).val()+"%23A%23";
        }
        for (var i = 0; i < temp3.length; i++) {
//ایمیل مهمانان خارج از سازمان
            temp4 += $(temp3[i]).val() + "%23A%23";
        }
        alert(temp2);
        alert(temp4);
        var param = "";
        param += "do=" + hmisSessions.tableName + ".edit";
        param += "&" + new jj('#swSessionsForm').jjSerial();
        param += "&id=" + new jj('#hmis_Sessions_id').jjVal();
        param += "&sessions_audience=" + temp2;
        param += "&sessions_audienceOutSide=" + temp4;
        new jj(param).jjAjax2(false);
        hmisSessions.m_show_tbl();
        hmisSessions.m_clean();
//        } else {
//            new jj(valid).jjDialog();
//        }
    },
//    m_validation: function () {// mohamdad
//        if (new jj("#content_title").jjVal().length < 1) {
//            return "فیلد عنوان نباید کوچکتر از دو کاراکتر باشد";
//        }
//        return "";
//    },
    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo(' hmisSessions.m_delete_after_question(' + id + ');\n', true, "");
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + hmisSessions.tableName + ".delete";
        param += "&" + hmisSessions.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisSessions.m_show_tbl();
        hmisSessions.m_clean();
    },
    m_select: function (id) {
        $('#newCommetteForm').show();
        $('#formInvitation').hide();
        var param = "";
        param += "do=" + hmisSessions.tableName + ".select";
        param += "&" + hmisSessions.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        hmisSessions.m_show_form();
    },
    m_add_EN: function (id) {
        var param = "";
        param += "do=" + hmisSessions.tableName + ".add_EN";
        param += "&" + hmisSessions.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        new jj("#" + hmisSessions.f_parent).jjVal(id);
        new jj("#" + hmisSessions.f_lang).jjVal("2");
        hmisSessions.m_show_form();
    },
    m_add_Ar: function (id) {
        var param = "";
        param += "do=" + hmisSessions.tableName + ".add_Ar";
        param += "&" + hmisSessions.f_id + "=" + (id == null ? "" : id);
        new jj(param).jjAjax2(false);
        new jj("#" + hmisSessions.f_parent).jjVal(id);
        new jj("#" + hmisSessions.f_lang).jjVal("3");
        hmisSessions.m_show_form();
    },
    //============ BY RASHIDI ========>
    m_add_Ln: function (id, langId) {
        var param = "";
        param += "do=" + hmisSessions.tableName + ".add_lang";
        param += "&" + hmisSessions.f_id + "=" + (id == null ? "" : id);
        param += "&myLang=" + (langId == null ? "1" : langId);
        new jj(param).jjAjax2(false);
        new jj("#" + hmisSessions.f_parent).jjVal(id);
        new jj("#" + hmisSessions.f_lang).jjVal(langId);
//        alert(id+"&&&&&"+langId);
        hmisSessions.m_show_form();
    },
    //<============ BY RASHIDI ========
    m_searchTextInTitle: function (text) {
        var param = "";
        param += "do=" + hmisSessions.tableName + ".searchTextInTitle";
        param += "&text=" + (text == null ? "" : text);
        new jj(param).jjAjax2(false);
    },
    m_searchTextInAll: function (text) {
        var param = "";
        param += "do=" + hmisSessions.tableName + ".searchTextInAll";
        param += "&text=" + (text == null ? "" : text);
        new jj(param).jjAjax2(false);
    },
    //============ BY RASHIDI ========>    
    m_searchTags: function () {
        var param = "";
        param += "do=" + hmisSessions.tableName + ".searchTags";
        param += "&" + new jj('#swSessionsForm').jjSerial();
//        param += "&panel=content_search_tags_result";
        new jj(param).jjAjax2(false);
    },
    m_insertTags: function () {
        var param = "";
        param += "do=" + hmisSessions.tableName + ".insertTags";
        param += "&" + new jj('#swSessionsForm').jjSerial();
//        param += "&panel=content_tags_div";
        new jj(param).jjAjax2(false);
    },
    //<============ BY RASHIDI ========    
    tabSizeTbl: function () {
        $('#swSessions').css('height', 520);
    },
    tabSizeForm: function () {
        $('#swSessions').css('height', 378);
    },
    /////////////////////shiran2////////////
    /**
     * 
     * @param {type} id sessions
     * @returns {undefined}
     */
    confirmationFinalSessions: function (id) {
        var temp = $('#audience input:checkbox[class=checkboxAudience]:checked'); //مدعوین سمت دار
        var temp3 = $('#audience input:checkbox[class=checkboxAudienceOutSide]:checked'); //مدعوین خارج از سازمان
        if (temp.size() == 0) {
            new jj("حضار در جلسه را مشخص نمایید").jjModal("پیام سامانه");
        } else {
            if (confirm("آیا از تایید و امضا صورت جلسه اطمینان دارید؟")) {
                hmisSessions.confirmationFinalSessions_after_question(id);
            } else {
            }
        }
    },

    confirmationFinalSessions_after_question: function (id) {
        var temp = $('#audience input:checkbox[class=checkboxAudience]:checked'); //مدعوین سمت دار
        var temp3 = $('#audience input:checkbox[class=checkboxAudienceOutSide]:checked'); //مدعوین خارج از سازمان
        var temp2 = "";
        var temp4 = "";
        for (var i = 0; i < temp.length; i++) {
            temp2 += $(temp[i]).attr('name') + "%23A%23"; //id user audience
//            temp2 +=$(temp[i]).val()+"%23A%23";
        }
        for (var i = 0; i < temp3.length; i++) {
//ایمیل مهمانان خارج از سازمان
            temp4 += $(temp3[i]).val() + "%23A%23";
        }
//        alert(temp2);
//        alert(temp4);
        var param = "";
        param += "&do=" + hmisSessions.tableName + ".confirmationFinalSessions";
        param += "&" + new jj('#swSessionsForm').jjSerial();
        param += "&id=" + new jj('#hmis_Sessions_id').jjVal();
        param += "&sessions_audience=" + temp2;
        param += "&sessions_audienceOutSide=" + temp4;
        new jj(param).jjAjax2(false);
//        hmisSessions.m_show_tbl();
        hmisSessions.m_clean();
    },
    /**
     * ای دی ُجلسات
     * دکمه ارسال به مسئولین اجرا
     * @param {type} id
     * @returns {undefined}
     */
    sendToTrackerAndExecutor: function (id) {

        if (confirm("آیا مصوبات صورتجلسه به مسئولین ابلاغ شود؟")) {
            hmisSessions.sendToTrackerAndExecutor_after_question(id);
        } else {
        }

    },
    sendToTrackerAndExecutor_after_question: function (id) {
        var param = "";
        param += "&id=" + id;
        param += "&do=" + hmisSessions.tableName + ".sendToTrackerAndExecutor";
        new jj(param).jjAjax2(false);
//        hmisSessions.m_show_tbl();
        hmisSessions.m_clean();
    },
//  

//    mainTabSetSize: function () {
////        var aa = $("#swContent").children();
////        var bb = 0;
////        for(i=0; i < aa.length; i++){  
////            if($(aa[i]).css("display")!='none'){
////                bb+= new jj($(aa[i]).css("height")).jjConvertToInt() ;
////            }
////        }
////        if(bb==0){
////            $('#tabs').css('height',572);
////        }else{
////            $('#tabs').css('height',bb+44);
////        }
//    }

};
//============ BY RASHIDI ========> 
//function selectSearchResult(selectedTagNo) {
//    $("#tags_name").val($("#tagsResult_td" + selectedTagNo).html());
//    $("#content_search_tags_result").hide();
//}

//function deleteContentTag(deletedTagNo) {
//    new jj("آیا از حذف این برچسب اطمینان دارید؟").jjDialog_YesNo('afterDeleteContentTag(' + deletedTagNo + ');\n', true, "");
//}
//function afterDeleteContentTag(deletedTagNo) {
//
////    var myString = $("#" + cmsContent.f_tags).val();
////    var oldWord = $("#contetn_tag_span" + deletedTagNo).html().toString();
////    var reg = new RegExp(oldWord, "g");
////    myString = myString.replace(reg, "");
////    alert(myString);
//
//    var str = $("#" + cmsContent.f_tags).val();
//    var tagName = $("#contetn_tag_span" + deletedTagNo).html().toString();
//    var reg = new RegExp(tagName, "g");
//    str = str.replace(reg, "");
//    $("#" + cmsContent.f_tags).val(str);
//    $("#contetn_tag_span" + deletedTagNo).remove();
//}
//<============ BY RASHIDI ========  