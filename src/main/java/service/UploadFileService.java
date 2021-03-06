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
	
	/**
	 * 根据 文件对象id 查找 上传文件对象
	 * @param fileId 文件对象id
	 * @return 匹配查询结果的上传文件对象。
	 * 			<br>当无查询结果 或 发生异常时，返回 null
	 */
	public UploadFileBo queryUploadFileById(Integer fileId) {
		return uploadFileDao.queryUploadFileById(fileId);
	}
}
