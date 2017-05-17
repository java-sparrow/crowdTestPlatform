package main.java.bo;

/**
 * 任务文档表 记录映射类
 *
 */
public class TaskDocBo extends BaseBo {
	/**
	 * 主键id
	 */
	private int id;
	/**
	 * 文档名
	 */
	private String docName;
	/**
	 * 文档地址
	 */
	private String docUrl;
	/**
	 * 所属的任务id
	 */
	private int taskId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getDocUrl() {
		return docUrl;
	}

	public void setDocUrl(String docUrl) {
		this.docUrl = docUrl;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
}
