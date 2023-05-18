package com.attech.contain;

public class AppConst {
	
	public static final String SUCCESS_CODE = "0";
	public static final String SUCCESS_DESC = "SUCCESS";
//	public static  String FAIL_CODE = "-1";
	public static final String FAIL_DESC = "FAIL";
	public static final String INVALID_REQUEST_PARAMS = "Invalid request params.";
	public static final String IS_NOT_ADMIN = "Is not admin";
	public static final String GOOGLE_CAPTCHA_FAIL = "Google captcha fail";
	
    public static String APPLICATION_PDF="application/pdf";
    public static String APPLICATION_DOC="application/vnd.openxmlformats-officedocument.wordprocessingml.document";
//    public static String SUCCESS_CODE="200";
    public static int SUCCESS=200;
    public static String FAIL_CODE="-1";
    public static String SUCCESS_DES="SUCCESS";
    public static String FAIL_DES="FAIL";
    public static final String USER_NAME_CE_DEV = "ce.username.dev";
	public static final String PASSWORD_CE_DEV = "ce.password.dev";
	public static final String CE_URL_DEV = "ce.ws.url.dev";
	public static final String FOLDER_ID_DEV = "folder.id.dev";
	
	public static final String USER_NAME_CE_SIT = "ce.username.sit";
	public static final String PASSWORD_CE_SIT = "ce.password.sit";
	public static final String CE_URL_SIT = "ce.ws.url.sit";
	public static final String FOLDER_ID_SIT = "folder.id.sit";
	
	public static final String USER_NAME_CE_UAT = "ce.username.uat";
	public static final String PASSWORD_CE_UAT = "ce.password.uat";
	public static final String CE_URL_UAT = "ce.ws.url.uat";
	public static final String FOLDER_ID_UAT= "folder.id.uat";
	public static final String CONFIG_MODE = "config.mode";
	public static final String USER_NAME_CE_LIVE = "ce.username.live";
	public static final String PASSWORD_CE_LIVE = "ce.password.live";
	public static final String CE_URL_LIVE = "ce.ws.url.live";
	public static final String FOLDER_ID_LIVE = "folder.id.live";
	public static final String ECM_FOLDER_ID = "qttd.ecmFolderId";
	public static final String TPBANK_FOLDER_ID = "qttd.tpbankFolderId";
	public static final String ROOT_FOLDER_ID = "qttd.rootFolderId";
	public static final String ROOT_FOLDER_NAME = "qttd.rootFolderName";
	public static final String ROOT_FOLDER_TMP_ID = "qttd.rootFolderTmpId";
	public static final String ROOT_FOLDER_TMP_NAME = "qttd.rootFolderTmpName";
	public static final String ROOT_PATH = "qttd.rootPath";
	public static final String ROOT_PATH_TMP = "qttd.rootPathTmp";
	public static final String REPOSITORY_ID = "qttd.repositoryId";
	public static final String REPOSITORY_TYPE = "qttd.repositoryType";
	public static final String LOG_GOP_FOLDER = "qttd.log.gopFolder.path";
	public static final String DEFAULT_ROOT_FOLDER_NAME = "QTTD";
	public static final String USER_NAME_CE = "ce.username";
	public static final String PASSWORD_CE = "ce.password";
	public static final String STANZA = "ce.stanza";
	public static final String URI = "ce.ws.url";
	public static final String OBJ_STORE = "ce.objectStore";
	public static final String OMNI_OBJ_STORE = "ce.omni.objectStore";
	public static final String CONNECTION_POINT = "ce.connectionPoint";
	public static final String DOMAIN = "ce.domain";
	public static final String ROSTER = "ce.roster";
	public static final String TEMP_FOLDER="folder.temp";	
	public static String CLASS_FOLDER = "QTTD_THU_MUC";
	public static String CLASS_DOCUMENT = "Document";
	
	//Ten loai ho so
	public static final String HOSO_CMC="HỒ SƠ CMC";
	public static final String HOSO_CRM="HỒ SƠ CRM";
	public static final String HOSO_COLLECTION="HỒ SƠ COLLECTION";
	public static final String COL_DONDOCNO_="COL_DONDOCNO";
	public static final String COL_THUGIU_="COL_THUGIU";
	public static final String COL_TOTUNG_="COL_TOTUNG";
	public static final String COL_DAUGIATSDB_="COL_DAUGIATSDB";
	public static final String CRM_DONDOCNO_="CRM_DONDOCNO";
	public static final String CRM_THUGIU_="CRM_THUGIU";
	public static final String CRM_DAUGIATSDB_="CRM_DAUGIATSDB";
	public static final String XLN_DONDOCNO_="XLN_DONDOCNO";
	public static final String XLN_THUGIU_="XLN_THUGIU";
	public static final String XLN_BANTAISAN_="XLN_BANTAISAN";
	public static final String XLN_KHOIKIEN_="XLN_KHOIKIEN";
	public static final String XLN_TOCAO_="XLN_TOCAO";
	public static final String XLN_THIHANHAN_="XLN_THIHANHAN";
	public static final String CMC_HSPL_="CMC_HSPL";
	public static final String CMC_HSTC_="CMC_HSTC";
	public static final String COL_DONDOCNO="Hồ sơ đôn đốc nợ";
	public static final String COL_THUGIU="Hồ sơ thu giữ";
	public static final String COL_TOTUNG="Hồ sơ tố tụng";
	public static final String COL_DAUGIATSDB="Hồ sơ bán đấu giá TSĐB";
	public static final String CRM_DONDOCNO="Hồ sơ đôn đốc nợ";
	public static final String CRM_THUGIU="Hồ sơ thu giữ";
	public static final String CRM_DAUGIATSDB="Hồ sơ bán đấu giá tài sản";
	public static final String XLN_DONDOCNO="Đôn đốc";
	public static final String XLN_THUGIU="Thu giữ tài sản đảm bảo";
	public static final String XLN_BANTAISAN="Bán tài sản";
	public static final String XLN_KHOIKIEN="Khởi kiện";
	public static final String XLN_TOCAO="Tố cáo công an";
	public static final String XLN_THIHANHAN="Thi hành án";
	public static final String CMC_HSPL="Hồ sơ pháp lý CMC";
	public static final String CMC_HSTC="Hồ sơ tài chính CMC";
	public static final String XLN_CMC="Hồ sơ XLN CMC";	
	
	// Date time format
	public static final String FORMAT_DATE_ONLY = "dd-MM-yyyy";
	public static final String FORMAT_DATE_TIME_12H = "dd-MM-yyyy hh:mm:ss aa";
	public static final String FORMAT_DATE_TIME_24H = "dd-MM-yyyy HH:mm:ss";
	
	// Record status
	public static final String RECORD_STATUS_ACTIVE = "A";
	public static final String RECORD_STATUS_INACTIVE = "I";
	public static final String RECORD_STATUS_LOCKED = "L";
	public static final String RECORD_STATUS_DELETED = "D";
	
	public static String ESB_CLIENT_IP = "esb.client.ip";
	public static String ESB_COMMON_USERNAME = "esb.common.additionalInformation.UserName";
	public static String ESB_COMMON_USERPASS = "esb.common.additionalInformation.UserPass";
	public static String ESB_CLIENT_HEADER_TARGET_ID = "esb.header.client.targetAppId";
	public static String ESB_CLIENT_HEADER_USER_ID = "esb.header.client.userId";
	public static String ESB_CLIENT_HEADER_PASSWORD = "esb.header.client.password";
	public static String ESB_CLIENT_HEADER_SOURCEAPP_ID = "esb.header.client.sourceAppId";
	public static String ESB_XREF = "esb.body.xref";
	public static String ESB_USER = "esb.body.user";
	public static String ESB_PASS = "esb.body.pass";
	public static String ESB_CODE = "esb.body.code";
	public static String ESB_FUNCTION_CODE = "esb.body.functionCode";
	public static String ESB_URL = "esb.url";
	public static String OTP_CONTENT = "otp.content";
	
	public static String BPM_URL = "bpm.url";

}
