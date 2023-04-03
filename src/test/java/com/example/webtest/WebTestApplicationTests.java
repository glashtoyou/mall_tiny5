package com.example.webtest;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.webtest.Dao.Game;
import com.example.webtest.Dao.User;
import com.example.webtest.Vo.HistoryVo;
import com.example.webtest.Vo.UserVo;
import com.example.webtest.mapper.GameMapper;
import com.example.webtest.mapper.UserMapper;
import com.example.webtest.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class WebTestApplicationTests {

	@Autowired
	UserMapper userMapper;

	@Autowired
	UserService userService;

	@Autowired
	GameMapper gameMapper;

	@Autowired
	HistoryService historyService;

	@Autowired
	RoleService roleService;

	@Autowired
	PermissionService permissionService;

	@Autowired
	UserRoleService userRoleService;

	@Autowired
	RolePermissionService rolePermissionService;

	@Autowired
	ApplyService applyService;

	@Autowired
	GameService gameService;

	@Test
	void insert(){
		//userMapper.insert(new User("643764","男",null,null,null));
		QueryWrapper<User>wrapper=new QueryWrapper<>();
		wrapper.eq("uname","joke");
		List<User>users=userMapper.selectList(wrapper);
		List<Integer>uids=new ArrayList<>();
		userService.list();
		for(User user:users){

			uids.add(user.getUid());
			System.out.println(user.toString());
		}
		System.out.println(uids.toString());

	}

	@Test
	void insertGame(){
		Timestamp a=Timestamp.valueOf("2018-12-18 09:32:32");//String convert to timestamp before() after()
		gameMapper.insert(new Game("爱过比赛","没有什么比你更值得","描述你记忆中的那个人",new Timestamp(System.currentTimeMillis()),a));
		System.out.println(gameMapper.selectList(null).toString());
		QueryWrapper<Game>wrapper=new QueryWrapper<>();
		wrapper.gt("gid",1);
		//Game game=gameMapper.selectOne(wrapper);
		//System.out.println(format(game.getGstart()));
		System.out.println(System.currentTimeMillis());
	}
	//generate timestamp

	@Test
	void selectHistory(){
		System.out.print(historyService.list().toString());
	}

	@Test
	void selectRole(){
		System.out.print(roleService.list().toString());
	}

	@Test
	void selectPermission(){
		System.out.print(permissionService.list().toString());
	}

	@Test
	void selectUserRole(){
		System.out.print(userRoleService.list().toString());
	}

	@Test
	void selectRolePermission(){
		System.out.print(rolePermissionService.list().toString());
	}

	@Test
	void test(){
		User user= (User) userService.loadUserByUsername("joke");
		System.out.println(user.toString());
	}

	@Test
	void UserVoTest(){
		System.out.println(userService.getListUserVo().toString());
	}

	@Test
	void UserVoAllTest(){
		List<UserVo>userVoList=userService.getListUserVo();
		userService.deleteBatchUser();
		userService.saveBatchUserVo(userVoList);
		System.out.println(userService.getListUserVo().toString());

	}

	@Test
	public void removeGameTest(){
		List<Game>gameList=applyService.getAllGames();
		gameMapper.delete(null);
		gameService.saveOrUpdateBatch(gameList);
		System.out.println(applyService.getAllGames().toString());
	}

	@Test
	public void removeHistoryTest(){
		List<HistoryVo>historyVos=historyService.getListHistoryVo();
		historyService.removeAllHistoryVo();
		historyService.saveListHistoryVo(historyVos);
		System.out.println(historyService.getListHistoryVo().toString());
	}

	@Test
	public void attendGamesTest(){
		System.out.println(applyService.attendGameByUsernameAndActivity("jokemai","中文演讲比赛"));
	}
}

