package com.springboot.web.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.web.demo.entity.Company;
import com.springboot.web.demo.service.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import java.util.List;

/**
 * (Company)表控制层
 *
 * @author makejava
 * @since 2019-04-25 16:58:34
 */
@RestController
@RequestMapping("company")
@Api(tags = "公司信息controller")
public class CompanyController {


    @Autowired
    private CompanyService companyService;



    /**
     * 根据id进行查询
     * @param id
     * @return
     */
    @GetMapping("getById")
    @ApiOperation(value = "根据id进行查询")
    public Company getById(@RequestParam Long id) {
        return companyService.getById(id);
    }


    /**
     * 新增公司信息
     * @param company
     * @return
     */
    @PostMapping
    @ApiOperation(value = "新增公司信息")
    public Boolean saveCompany(@RequestBody Company company) {
        return companyService.save(company);
    }



    /**
     * 修改公司信息
     * @param company
     * @return
     */
    @PutMapping
    @ApiOperation(value = "修改公司信息")
    public Boolean updateCompany(@RequestBody Company company) {
        return companyService.updateById(company);
    }

    /**
     * 删除公司信息
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除公司信息")
    public Boolean delete(@PathVariable Long id) {
        return companyService.removeById(id);
    }




    /**
     * 根据条件查询
     * @return
     */
    @GetMapping("getByCondition")
    @ApiOperation(value = "根据条件查询")
    public List<Company> getByCondition(@ApiParam(value = "公司名称") @RequestParam String companyName,
                                        @ApiParam(value = "公司地址") @RequestParam String companyAddress) {
        QueryWrapper<Company> qw = new QueryWrapper<Company>().select("company_name","bank")
                .eq("company_name",companyName)
                .eq("bank","string")
                .like("company_address",companyAddress);
        return companyService.list(qw);
    }



    /**
     * 分页查询
     * @param current
     * @param size
     * @return
     */
    @GetMapping("getPage")
    @ApiOperation(value = "分页查询")
    public IPage<Company> getPage(@RequestParam Long current, @RequestParam Long size) {
        QueryWrapper<Company> qw = new QueryWrapper<Company>().eq("company_name","电科院");
        return companyService.page(new Page<>(current,size),qw);
    }



    /**
     * 根据Lambda表达式查询
     * @return
     */
    @GetMapping("getByLambda")
    @ApiOperation(value = "根据Lambda表达式查询")
    public List<Company> getByLambda() {
        LambdaQueryWrapper<Company> qw = new QueryWrapper<Company>()
                .lambda()
                .eq(Company::getBank, "开户银行")
                .like(Company::getCompanyName,"电");
        return companyService.list(qw);
    }






}