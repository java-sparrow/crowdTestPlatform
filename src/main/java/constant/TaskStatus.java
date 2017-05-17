package main.java.constant;

/**
 * 任务状态常量类<br><br>
 * 
 * 下面是任务状态与文本对应：<br>
 * 1  --> 任务创建，未被领取；<br>
 * 10 --> 任务初次被领取，但未完成；<br>
 * 20 --> 任务已完成/提交，等待审批；<br>
 * 15 --> 审批未通过，可以再次提交；<br>
 * 30 --> 审批通过，任务不可操作。
 */
public class TaskStatus {
	// 任务刚创建时的状态
	public static final int CREATED_CODE = 1;
	public static final String CREATED_TEXT = "";
	// 任务被领取（但未完成）时的状态
	public static final int ACCEPTED_CODE = 10;
	public static final String ACCEPTED_TEXT = "任务进行中";
	// 任务已完成（等待审批）时的状态
	public static final int WAIT_APPROVE_CODE = 20;
	public static final String WAIT_APPROVE_TEXT = "等待审批";
	// 任务被审批但未通过时的状态
	public static final int APPROVED_REJECT_CODE = 15;
	public static final String APPROVED_REJECT_TEXT = "审批未通过";
	// 任务被审批且通过时的状态
	public static final int APPROVED_PASS_CODE = 30;
	public static final String APPROVED_PASS_TEXT = "审批通过";
	
	/**
	 * 根据状态码获取对应的状态文本
	 * @param code 状态码
	 * @return 对应的状态文本
	 */
	public static String getTextByCode(int code) {
		switch (code) {
			case CREATED_CODE: {
				return CREATED_TEXT;
			}
			case ACCEPTED_CODE: {
				return ACCEPTED_TEXT;
			}
			case WAIT_APPROVE_CODE: {
				return WAIT_APPROVE_TEXT;
			}
			case APPROVED_REJECT_CODE: {
				return APPROVED_REJECT_TEXT;
			}
			case APPROVED_PASS_CODE: {
				return APPROVED_PASS_TEXT;
			}
			default: {
				// 使用初始状态的文本
				return CREATED_TEXT;
			}
		}
	}
}
