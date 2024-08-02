package util;

public class SchemaConstants {
	// Database Name
    public static final String DATABASE_NAME = "auromatherapy_massage";
    
	// Table Names
	public static final String TABLE_POST = "POST";
	public static final String TABLE_POST_TAG = "POST_TAG";
	public static final String TABLE_POST_STATUS = "POST_STATUS";
	public static final String TABLE_TAG = "TAG";
	public static final String TABLE_SERVICE = "SERVICE";
	public static final String TABLE_EMPLOYEE = "EMPLOYEE";
	public static final String TABLE_COURSE = "COURSE";

	// POST Table Columns
	public static final String POST_ID = "post_id";
	public static final String POST_EMPLOYEE_ID = "employee_id";
	public static final String POST_STATUS = "post_status";
	public static final String POST_LAST_UPDATE_EMPLOYEE = "post_last_update_employee";
	public static final String POST_LAST_UPDATE_TIME = "post_last_update_time";
	public static final String POST_TITLE = "post_title";
	public static final String POST_CONTENT = "post_content";
	public static final String POST_SLUG = "post_slug";
	public static final String POST_INTRO_IMG_SRC = "post_intro_img_src";
	public static final String POST_EXCERPT = "post_excerpt";
	public static final String POST_EXCERPT_IMG_SRC = "post_excerpt_img_src";
	public static final String POST_CREATE_TIME = "post_create_time";

	// POST_TAG Table Columns
	public static final String POST_TAG_ID = "post_tag_id";
	public static final String POST_TAG_POST_ID = "post_id";
	public static final String POST_TAG_TAG_ID = "tag_id";

	// POST_STATUS Table Columns
	public static final String POST_STATUS_ID = "post_status_id";
	public static final String POST_STATUS_NAME = "post_status_name";

	// TAG Table Columns
	public static final String TAG_ID = "tag_id";
	public static final String TAG_NAME = "tag_name";

	// SERVICE Table Columns
	public static final String SERVICE_ID = "service_id";
	public static final String SERVICE_EMPLOYEE_ID = "employee_id";
	public static final String SERVICE_TITLE = "service_title";
	public static final String SERVICE_INFO = "service_info";
	public static final String SERVICE_IMG_SRC = "service_img_src";
	public static final String SERVICE_PRICE = "service_price";
	public static final String SERVICE_CREATE_TIME = "service_create_time";

	// EMPLOYEE Table Columns
	public static final String EMPLOYEE_ID = "employee_id";
	public static final String EMPLOYEE_NAME = "employee_name";
	public static final String EMPLOYEE_TITLE = "employee_title";
	public static final String EMPLOYEE_INFO = "employee_info";
	public static final String EMPLOYEE_IMG_SRC = "employee_img_src";

	// COURSE Table Columns
	public static final String COURSE_ID = "course_id";
	public static final String COURSE_EMPLOYEE_ID = "employee_id";
	public static final String COURSE_TITLE = "course_title";
	public static final String COURSE_CONTENT = "course_content";
	public static final String COURSE_IMG_SRC = "course_img_src";
	public static final String COURSE_CREATE_DATE = "course_create_date";

	private SchemaConstants() {
		// Private constructor to prevent instantiation
	}
}
