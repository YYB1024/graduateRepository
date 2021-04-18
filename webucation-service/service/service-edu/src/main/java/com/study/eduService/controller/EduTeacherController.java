package com.study.eduService.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.eduService.entity.EduTeacher;
import com.study.eduService.entity.vo.TeacherQuery;
import com.study.eduService.service.EduTeacherService;
import com.study.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 * 1、创建controller
 *
 * @author yangyanbin
 * @since 2021-04-17
 */

/**
 * @author WEIYI
 * @RestController:交给Spring管理并要求返回的数据为JSon
 * @RequestMapping：请求访问的路径
 * @CrossOrigin:解决跨域问题
 * @Api：友好的中文提示
 */
@SuppressWarnings("all")
@Api(description = "讲@CrossOrigin师管理")
@RestController
@RequestMapping("/eduService/teacher")
@CrossOrigin
public class EduTeacherController {
    //访问地址：http://localhost:8001//eduService/teacher/findAll
    //1、Controller层调用service层把service层注入
    @Autowired
    private EduTeacherService teacherService;

    /***
     *@Desc:
     * 1、查询讲师表的所有数据(测试)
     * rest风格 @GetMapping:get 请求
     *@param
     *@return:List<EduTeacher>
     *     R:返回统一格式
     */
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public R findAllTeacher() {
        //调用service的方法实现查询所有的操作
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("teachers", list);
    }

    /**
     * 2、逻辑删除讲师的方法（Delete提交）
     * id值需要通过路径进行传递
     *
     * @param id:获得路径中id值 get提交可以在浏览器路径中测试，而post、delete、put不可以，可以使用swagger测试
     * @PathVariable：获得路径的值
     */
    @ApiOperation(value = "逻辑根据ID删除讲师")
    @DeleteMapping("{id}")
    public R removeTeacher(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id) {
        boolean flag = teacherService.removeById(id);
        if (flag) {
            return R.ok();

        } else {
            return R.error();
        }
    }

    /***
     *@Desc:3、分页查询所有讲师的方法
     *@param:current:当前页，limit：每页的记录数
     *@return:com.study.utils.R
     */
    @ApiOperation(value = "分页讲师列表")
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(
            @ApiParam(name = "current", value = "当前页码", required = true)
            @PathVariable long current,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable long limit) {
        /**创建page对象*/
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        /**调用方法实现分页*/
        /**调用方法的时候，底层封装，把分页所有数据封装到pageTeacher对象里面*/
        teacherService.page(pageTeacher, null);
        //总记录数
        long total = pageTeacher.getTotal();
        //数据list集合
        List<EduTeacher> records = pageTeacher.getRecords();
        return R.ok().data("total", total).data("rows", records);
    }

    /***
     *@Desc:4、多条件组合查询讲师
     *@param current，limit，teacherQuery
     *@return:R
     * @RequestBody:使用json传递数据，把JSON数据封装到对应对象里面(Post提交)
     */
    @ApiOperation(value = "多条件组合查询讲师")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(
            @ApiParam(name = "current", value = "当前页码", required = true)
            @PathVariable long current,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable long limit,
            @RequestBody(required = false) TeacherQuery teacherQuery) {
        /**1、创建page对象*/
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        /**2、构建条件*/
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        /**2、1多条件组合查询：使用动态SQL（判断条件值是否为空，若不为空拼接条进）*/
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        /**2、2判断条件值是否为空，若不为空拼接条进*/
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);

        }
        if (!StringUtils.isEmpty(level)) {
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create", end);
        }
        /**3、调用方法实现分页*/
        teacherService.page(pageTeacher, wrapper);

        /**4、总记录数*/
        long total = pageTeacher.getTotal();
        /**5、数据list集合*/
        List<EduTeacher> records = pageTeacher.getRecords();
        /**6、返回*/
        return R.ok().data("total", total).data("rows", records);
    }

    /***
     *@Desc:5、添加讲师
     *@param eduTeacher
     *@return:com.study.utils.R
     */
    @ApiOperation(value = "添加讲师")
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = teacherService.save(eduTeacher);
        if(save){
            return  R.ok();

        }else {
            return R.error();
        }

    }

    /***
     *@Desc:6、根据讲师id进查询数据回显
     *@param id
     *@return:
     */
    @ApiOperation(value = "根据讲师id进查询讲师")
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable String id){
        //制造异常
        /*try{
            int a = 10/0;
        }catch (Exception e){
            //执行自定义异常(需手动抛出)
            throw new GuliException(20001,"执行了自定义异常处理...");

        }*/


        EduTeacher eduTeacher = teacherService.getById(id);
        return R.ok().data("teacher",eduTeacher);
    }

    /***
     *@Desc: 7、讲师修改功能
     *@param null
     *@return: R
     */
    @ApiOperation(value = "讲师修改功能")
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean flag = teacherService.updateById(eduTeacher);
        if(flag){
            return  R.ok();
        }else {
            return R.error();
        }

    }
}

