package com.springboot.web.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.web.demo.entity.Company;
import com.springboot.web.demo.dao.CompanyDao;
import com.springboot.web.demo.service.CompanyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Company)表服务实现类
 *
 * @author makejava
 * @since 2019-04-25 16:58:34
 */
@Service("companyService")
public class CompanyServiceImpl extends ServiceImpl<CompanyDao,Company> implements CompanyService {

}