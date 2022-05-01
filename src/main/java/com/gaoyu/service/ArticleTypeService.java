package com.gaoyu.service;

import com.gaoyu.entity.User;
import com.gaoyu.util.UpdateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaoyu.entity.ArticleType;
import com.gaoyu.repository.ArticleTypeRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleTypeService {

	@Autowired
	private ArticleTypeRepository articleTypeRepository;
	
	public ArticleType addArticleType(ArticleType articleType) {
		return articleTypeRepository.save(articleType);
	}



	public List<ArticleType> findAllArticleType(User user){
		return articleTypeRepository.findAllByUserIs(user);
	}

	public void deleteArticleType(ArticleType articleType){
		ArticleType oldArticleType=articleTypeRepository.getById(articleType.getArticleTypeId());

		if(oldArticleType!=null){
			UpdateUtil.copyNullProperties(articleType,oldArticleType);
		}
		oldArticleType.setUser(null);
		//外键user设置为空，完成删除
		articleTypeRepository.save(oldArticleType);
	}

	//使用一个方法类，将前端传递过来的不为空的参数，就是要修改的值copy复制来覆盖原始对象
	//即修改的进行修改，不修改的保持不变。
	public List<ArticleType> modifyArticleType(ArticleType articleType){

		ArticleType oldArticleType=articleTypeRepository.getById(articleType.getArticleTypeId());

		if(oldArticleType!=null){
			UpdateUtil.copyNullProperties(articleType,oldArticleType);
		}
		articleTypeRepository.save(oldArticleType);
		return articleTypeRepository.findAllByUserIs((User)articleType.getUser());
	}
	public boolean isExistTypeName(User user,ArticleType articleType){
		return articleTypeRepository.existsArticleTypeByUserAndArticleTypeNameIs(user,articleType.getArticleTypeName());
	}
	public ArticleType findTypeById(int typeId){
		return articleTypeRepository.getById(typeId);
	}
}
