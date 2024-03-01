package com.guet.bishe.controller;


import com.guet.bishe.entity.Authority;
import com.guet.bishe.service.AuthorityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 权限;(authority)表控制层
 *
 * @author : cardo
 * @date : 2024-2-28
 */
@Api(tags = "权限对象功能接口")
@RestController
@RequestMapping("/authority")
public class AuthorityController {
    @Autowired
    private AuthorityService authorityService;

    /**
     *
     * @return 权限列表
     */
    @ApiOperation("返回权限列表")
    @GetMapping("/all")
    public ResponseEntity<List<Authority>> queryAll() {
        return ResponseEntity.ok(authorityService.queryAll());
    }

    /**
     * 新增数据
     *
     * @param authority 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public ResponseEntity<Boolean> add(@RequestBody Authority authority) {
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
    public ResponseEntity<Boolean> edit(@RequestBody Authority authority) {
        return ResponseEntity.ok(authorityService.update(authority));
    }

    /**
     * 通过主键删除数据
     *
     * @param authorityId 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键删除数据")
    @DeleteMapping("{authorityId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable String authorityId) {
        return ResponseEntity.ok(authorityService.deleteByAuthorityId(authorityId));
    }
}