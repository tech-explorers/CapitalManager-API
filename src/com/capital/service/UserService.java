package com.capital.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capital.constants.GeneralConstants;
import com.capital.dao.UserDao;
import com.capital.entities.LoginEntity;
import com.capital.entities.UserEntity;
import com.capital.model.ResponseModel;
import com.capital.model.UserResponse;
import com.capital.util.GeneralUtil;

@Service
@Transactional
public class UserService implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Autowired
	private UserDao dao;

	static Logger logger = Logger.getLogger(UserService.class.getName());

	public List<LoginEntity> getAllUsers() {
		logger.info("Seq Value : " + dao.seqNextVal("group_groupid"));
		return dao.getAllUsers();
	}

	public ResponseModel<UserResponse> registerUser(UserEntity userEntity) {
		@SuppressWarnings("unchecked")
		ResponseModel<UserResponse> responseModel = (ResponseModel<UserResponse>) applicationContext
				.getBean(ResponseModel.class);
		String loginID = dao.seqNextVal("login_details_loginid");
		userEntity.setUserId(GeneralConstants.STRING_ID);
		userEntity.getLoginEntity().setEmailId(userEntity.getEmailId());
		userEntity.getLoginEntity().setLoginid(loginID);

		boolean result = dao.registerUser(userEntity);
		logger.info("The register result is -----" + result);
		if (result) {
			// Create Response Object
			UserResponse userResponse = applicationContext.getBean(UserResponse.class);
			userResponse.setFirstName(userEntity.getFirstName());
			userResponse.setMiddleName(userEntity.getMiddleName());
			userResponse.setLastName(userEntity.getLastName());

			logger.info("User response : " + userEntity.getFirstName());
			responseModel.setResponseObject(userResponse);
			responseModel.setResponseMessage(GeneralConstants.REGISTER_SUCCESS_MSG);
			responseModel.setResponseStatus(GeneralConstants.STATUS_SUCCESS);
			logger.info("Response Object" + responseModel);
		}

		else {
			responseModel.setResponseMessage(GeneralConstants.REGISTER_FAIL_MSG);
			responseModel.setResponseStatus(GeneralConstants.STATUS_FAILURE);
		}

		return responseModel;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public ResponseModel<UserResponse> loginUser(LoginEntity loginEntity) {
		boolean authUserFlag = false;
		ResponseModel<UserResponse> responseModel = applicationContext.getBean(ResponseModel.class);
		boolean findUserFlag = dao.findUser(loginEntity.getEmailId());
		if (findUserFlag) {
			authUserFlag = dao.authenticateUser(GeneralUtil.getHashedValue(loginEntity.getPassword()));
		}
		else {
			responseModel.setResponseMessage("User not registered!");
			responseModel.setResponseStatus(GeneralConstants.STATUS_FAILURE);
			return responseModel;
		}
		if (authUserFlag) {
			UserResponse userResponse = applicationContext.getBean(UserResponse.class);
			UserEntity userEntity = dao.fetchUserDetails(loginEntity.getEmailId());
			logger.info("UserEntity while performing login : ----------------------------------------------"+userEntity);
			userResponse.setFirstName(userEntity.getFirstName());
			userResponse.setMiddleName(userEntity.getMiddleName());
			userResponse.setLastName(userEntity.getLastName());
			userResponse.setEmailID(userEntity.getEmailId());
			//group names would be populated at a later time
			responseModel.setResponseMessage("Successfully logged in!");
			responseModel.setResponseStatus(GeneralConstants.STATUS_SUCCESS);
			responseModel.setResponseObject(userResponse);
		}
		else {
			responseModel.setResponseMessage("Login unsuccessful!");
			responseModel.setResponseStatus(GeneralConstants.STATUS_FAILURE);
		}
		return responseModel;
	}

}
