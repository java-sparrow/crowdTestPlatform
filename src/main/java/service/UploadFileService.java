package main.java.service;

import main.java.bo.UploadFileBo;
import main.java.dao.UploadFileDao;

public class UploadFileService extends BaseService {
	private UploadFileDao uploadFileDao = new UploadFileDao();

	/**
	 * 新增 上传文件
	 * @param uploadFileData 上传文件数据对象
	 * @return 新增成功时，返回 新记录的id。<br>
	 * 			新增失败时，返回 null。
	 */
	public Integer addUploadFile(UploadFileBo uploadFileData) {
		return uploadFileDao.addUploadFile(uploadFileData);
	}
}
