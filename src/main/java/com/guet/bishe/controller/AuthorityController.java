package com.guet.bishe.controller;

import java.util.List;

import com.guet.bishe.entity.Authority;
import com.guet.bishe.service.AuthorityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


 /**
 * 权限;(authority)表控制层
 * @author : cardo
 * @date : 2024-2-28
 */
@Api(tags = "权限对象功能接口")
@RestController
@RequestMapping("/authority")
public class AuthorityController{
    @Autowired
    private AuthorityService authorityService;
    
    /** 
     * 通过ID查询单条数据 
     *
     * @param authorityId 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{authorityId}")
    public ResponseEntity<Authority> queryById(@PathVariable String  authorityId){
        return ResponseEntity.ok(authorityService.queryByAuthorityId(authorityId));
    }
    
    /** 
     * 分页查询
     *
     * @param authority 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @ApiOperation("分页查询")
    @GetMapping
    public ResponseEntity<PageImpl<Authority>> paginQuery(Authority authority, PageRequest pageRequest){
        //1.分页参数
        long current = pageRequest.getPageNumber();
        long size = pageRequest.getPageSize();
        //2.分页查询
        /*把Mybatis的分页对象做封装转换，MP的分页对象上有一些SQL敏感信息，还是通过spring的分页模型来封装数据吧*/
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Authority> pageResult = authorityService.paginQuery(authority, current,size);
        //3. 分页结果组装
        List<Authority> dataList = pageResult.getRecords();
        long total = pageResult.getTotal();
        PageImpl<Authority> retPage = new PageImpl<Authority>(dataList,pageRequest,total);
        return ResponseEntity.ok(retPage);
    }
    
    /** 
     * 新增数据
     *
     * @param authority 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public ResponseEntity<Authority> add(Authority authority){
        return ResponseEntity.ok(authorityService.insert(authority));
    }
    
    /** 
     * 更新数据
     *
     * @param authority 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public ResponseEntity<Authority> edit(Authority authority){
        return ResponseEntity.ok(authorityService.update(authority));
    }
    
    /** 
     * 通过主键删除数据
     *
     * @param authorityId 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键删除数据")
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String  authorityId){
        return ResponseEntity.ok(authorityService.deleteByAuthorityId(authorityId));
    }
}