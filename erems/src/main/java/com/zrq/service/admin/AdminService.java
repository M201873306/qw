package com.zrq.service.admin;

import com.zrq.dao.admin.AdminDao;
import com.zrq.entity.*;
import com.zrq.service.BaseService;
import com.zrq.util.ConvertUtil;
import com.zrq.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by zrq on 2018-5-12.
 */
@Service
public class AdminService extends BaseService {
    @Autowired
    private AdminDao adminDao;

    /**
     * 查询所有考点区域
     * @return
     */
    public List<Address> findArea() {
        List<Address> result=null;
        String url=this.getBaseUrl("user-service");
        url+="/user/area";
        ResponseEntity<ResResult> response = restTemplate.getForEntity(url,ResResult.class);
        if(response.getBody().getCode()==200){
            List list=(List)response.getBody().getData();
            if(list.size()<=0){
                return null;
            }
            result=new ArrayList<Address>();
            Iterator it = list.iterator();
            while(it.hasNext()) {
                result.add(ConvertUtil.parseMap2Object((Map<String, Object>)it.next(),Address.class));
            }
        }
        return result;
//        return adminDao.findArea();
    }

    /**
     * 根据考点名及考试区域查询
     * @param name
     * @param area
     * @return
     */
    public List<Room> searchByNameAndArea(String name, Integer area) {
        List<Room> result=null;
        String url=this.getBaseUrl("user-service")+"/user/search";
        Map<String,Object> map=new HashMap<>();
        map.put("sname",name);
        map.put("sarea",area);
        url+=ConvertUtil.map2Url(map);
        ResponseEntity<ResResult> response = restTemplate.getForEntity(url,ResResult.class);
        if(response.getBody().getCode()==200){
            List list=(List)response.getBody().getData();
            if(list.size()<=0){
                return null;
            }
            result=new ArrayList<Room>();
            Iterator it = list.iterator();
            while(it.hasNext()) {
                result.add(ConvertUtil.parseMap2Object((Map<String, Object>)it.next(),Room.class));
            }
        }
        return result;
//        return adminDao.searchByNameAndArea(name,area);
    }

    /**
     * 根据id查找考点信息
     * @param id
     * @return
     */
    public Room findRoomById(Integer id) {
        Room result=null;
        String url=this.getBaseUrl("user-service");
        url+="/user/room?id="+id;
        ResponseEntity<ResResult> response = restTemplate.getForEntity(url,ResResult.class);
        if(response.getBody().getCode()==200){
            result=(Room)ConvertUtil.parseMap2Object((Map<String, Object>)response.getBody().getData(),Room.class);
        }
        return result;
//        return adminDao.findRoomById(id);
    }

    /**
     * 根据id更新考点信息
     * @param room
     * @return
     */
    public int updateRoom(Room room) {
        Integer result=0;
        String url=this.getBaseUrl("user-service");
        url+="/user/saveRoom";
        url+=ConvertUtil.map2UrlRoom(room);
//        url+="&address.id="+room.getAddress().getId();
        ResponseEntity<ResResult> response = restTemplate.getForEntity(url,ResResult.class);
        if(response.getBody().getCode()==200){
            result=1;
        }
        return result;
//        return adminDao.updateRoom(room);
    }

    /**
     * 新增考点信息
     * @param room
     * @return
     */
    public int saveRoom(Room room) {
        Integer result=0;
        String url=this.getBaseUrl("user-service");
        url+="/user/saveRoom";
        url+=ConvertUtil.map2UrlRoom(room);
//        url+="&address.id="+room.getAddress().getId();
        ResponseEntity<ResResult> response = restTemplate.getForEntity(url,ResResult.class);
        if(response.getBody().getCode()==200){
            result=1;
        }
        return result;
//        return adminDao.saveRoom(room);
    }

    /**
     * 删除考点
     * @param room
     * @return
     */
    public int deleteRoom(Room room) {
        Integer result=0;
        String url=this.getBaseUrl("user-service");
        url+="/user/deleteRoom?id="+room.getId();
        ResponseEntity<ResResult> response = restTemplate.getForEntity(url,ResResult.class);
        if(response.getBody().getCode()==200){
            result=1;
        }
        return result;
//        return adminDao.deleteRoom(room);
    }

    /**
     * 根据某项考试查找所有考生成绩
     * @param sexam
     * @return
     */
    public List<MyExam> findScoreByExam(Integer sexam) {
//        List<MyExam> result=null;
//        String url=this.getBaseUrl("exam-service");
//        if(sexam!=null) {
//            url += "/score/findScoreByExam?eid=" + sexam;
//        }else{
//            url+="/score/findScoreByExam";
//        }
//        ResponseEntity<ResResult> response = restTemplate.getForEntity(url,ResResult.class);
//        if(response.getBody().getCode()==200){
//            List list=(List)response.getBody().getData();
//            if(list.size()<=0){
//                return null;
//            }
//            result=new ArrayList<MyExam>();
//            Iterator it = list.iterator();
//            while(it.hasNext()) {
//                result.add(ConvertUtil.parseMap2Object((Map<String, Object>)it.next(),MyExam.class));
//            }
//        }
//        return result;
        return adminDao.findScoreByExam(sexam);
    }

    /**
     * 根据个人考试成绩项id查询单条信息
     * @param id
     * @return
     */
    public MyExam findScoreById(Integer id) {
//        MyExam result=null;
//        String url=this.getBaseUrl("exam-service");
//        url+="/score/queryPersonExamByMyExam?id="+id;
//        ResponseEntity<ResResult> response = restTemplate.getForEntity(url,ResResult.class);
//        if(response.getBody().getCode()==200){
//            result=(MyExam)ConvertUtil.parseMap2Object((Map<String, Object>) response.getBody().getData(),MyExam.class);
//        }
//        return result;
        return adminDao.findScoreById(id);
    }

    /**
     * 更新个人考试成绩
     * @param myExam
     * @return
     */
    public int updateScore(MyExam myExam) {
        Integer result=0;
        String url=this.getBaseUrl("exam-service");
        url+="/score/updateScore?id="+myExam.getId()+"&score="+myExam.getScore();
        ResponseEntity<ResResult> response = restTemplate.getForEntity(url,ResResult.class);
        if(response.getBody().getCode()==200){
            result=1;
        }
        return result;
//        return adminDao.updateScore(myExam);
    }

    /**
     * 批量生成某考试的考号
     * @return
     */
    public int batchCreateExamNum(Integer examId) {
        List<MyExam> e=adminDao.findScoreByExam(examId);
        //创建分地区存储考生信息图
        Map<Integer,List<MyExam>> areaMap=new TreeMap<Integer,List<MyExam>>();
        //创建分地区编号图----可不用
        Map<Integer,String> areaNumMap=new TreeMap<Integer,String>();
        //创建考点信息图
        Map<Integer,List<Room>> examMap=new TreeMap<Integer,List<Room>>();
        System.out.println("某考试的考生记录:"+e.size());
        /**
         * 步骤1：建立以地区为分类的不同地区考生列表
         * 1.分地区存储考生信息图
         * 2.分地区编号图
         */
        for(MyExam m:e) {//循环遍历获得的某考试的考生记录，按地区划分考生为不同列表
            Integer id=m.getUser().getAddress().getId();
            if(areaMap.containsKey(id)){
                areaMap.get(id).add(m);
            }else {
                List<MyExam> l=new ArrayList<MyExam>();
                l.add(m);
                areaMap.put(id, l);
                areaNumMap.put(id,m.getAddress().getNum());
            }
        }
        System.out.println("xxx:"+areaMap);
        System.out.println("分地区考生信息图:"+areaMap.keySet());
        /**
         * 步骤2：按地区获得考点信息图
         */
        for (Integer s:areaMap.keySet()) {
            List<Room> el=adminDao.searchByNameAndArea(null,s);
            examMap.put(s,el);
        }
        System.out.println("考点信息图:"+examMap);
        /**
         * 步骤3：对应地区考生考号分配（单地区考点位置数量一定大于该地区考生总数）
         * 1.每个考室大小为30
         * 2.获得某地区考生记录
         * 3.根据获得的考点信息安排考生
         */
        for(Map.Entry<Integer,List<MyExam>> ml:areaMap.entrySet()){//分地区考生遍历
            Integer id=ml.getKey();
            List<MyExam> tempUser=ml.getValue();
            int tsize=tempUser.size();
            List<Room> tempRoom=examMap.get(id);
            int i=0,count=0;//i考点序号增加，count记录当前已编码考生数
            outer:while(true) {//跳出循环标号，已尝试该循环只能跳到for(两层)，无法跳出while
                for (int j = 1; j < tempRoom.get(i).getSize(); j++) {
                    String rnum = StringUtil.changeToString(j, 2);//考点考室编号
                    for (int k = 1; k <= 30; k++) {
                        String pnum = StringUtil.changeToString(k, 2);//位置编号
                        tempUser.get(count).setExamNum(tempRoom.get(i).getAddress().getNum() +
                                tempRoom.get(0).getNum() + rnum + pnum);
                        tempUser.get(count).setRoomNum(tempRoom.get(i).getAddress().getNum() +
                                tempRoom.get(0).getNum() + rnum);
                        count++;
                        if(count>=tsize){
                            break outer;
                        }
                    }
                }
                i++;//此处可优化判断是否需要增加考点，或初始设计逻辑自动检测需要的考点
            }
        }
        for(MyExam ee:e){
            System.out.println("考号列表："+ee.getExamNum()+ee.getUser().getName());
        }
        int x=adminDao.batchCreateExamNum(e);
        return x;
    }


    /**
     * 查找用户列表
     * @param user
     * @return
     */
    public List<User> findUser(User user) {
        List<User> result=null;
        String url=this.getBaseUrl("user-service");
        url+="/user/list?role="+user.getRole();
        ResponseEntity<ResResult> response = restTemplate.getForEntity(url,ResResult.class);
        if(response.getBody().getCode()==200){
            List list=(List)response.getBody().getData();
            if(list.size()<=0){
                return null;
            }
            result=new ArrayList<User>();
            Iterator it = list.iterator();
            while(it.hasNext()) {
                result.add(ConvertUtil.parseMap2Object((Map<String, Object>)it.next(),User.class));
            }
        }
        return result;
//        return adminDao.findUser(user);
    }

    /**
     * 生成新用户
     * @param user
     * @return
     */
    public Integer saveUser(User user) {
        Integer result=0;
        String url=this.getBaseUrl("user-service");
        url+="/user/saveUser";
        if(user.getPassword()==null){
            user.setPassword(user.getUsername());
        }
        url+= ConvertUtil.map2Url(user);
        ResponseEntity<ResResult> response = restTemplate.getForEntity(url,ResResult.class);
        if(response.getBody().getCode()==200){
            result=1;
        }
        return result;
//        return adminDao.saveUser(user);
    }

    /**
     * 删除用户
     * @param user
     * @return
     */
    public Integer deleteUser(User user) {
        Integer result=0;
        String url=this.getBaseUrl("user-service");
        url+="/user/deleteUser?id="+user.getId();
        ResponseEntity<ResResult> response = restTemplate.getForEntity(url,ResResult.class);
        if(response.getBody().getCode()==200){
            result=1;
        }
        return result;
//        return adminDao.deleteUser(user);
    }
}
