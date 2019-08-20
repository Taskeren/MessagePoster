package cn.glycol.bot.poster;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.sobte.cqp.jcq.entity.ICQVer;
import com.sobte.cqp.jcq.entity.IMsg;
import com.sobte.cqp.jcq.entity.IRequest;
import com.sobte.cqp.jcq.event.JcqAppAbstract;

public class App extends JcqAppAbstract implements ICQVer, IMsg, IRequest {
	
	public App() {
		super();
	}

	@Override
	public String appInfo() {
		return CQAPIVER + "," + "cn.glycol.bot.poster.app";
	}

	public final List<String> messages = new ArrayList<String>();
	
	public String rollMessage() {
		return messages.get(new Random().nextInt(messages.size()));
	}
	
	@Override
	public int startup() {
		try {
			File file = new File("data.dat");
			if(!file.exists()) file.createNewFile();
			List<String> lines = Files.readAllLines(file.toPath());
			messages.addAll(lines);
		} catch (IOException ex) {
			System.out.println("数据读取失败！");
			ex.printStackTrace();
		}
		return 0;
	}

	@Override
	public int exit() {
		return 0;
	}

	@Override
	public int enable() {
		return 0;
	}

	@Override
	public int disable() {
		return 0;
	}

	@Override
	public int privateMsg(int subType, int msgId, long fromQQ, String msg, int font) {
		return 0;
	}

	@Override
	public int groupMsg(int subType, int msgId, long fromGroup, long fromQQ, String fromAnonymous, String msg, int font) {
		if(msg.contentEquals("/say")) {
			String ret = CC.at(fromQQ) + "\n" + rollMessage();
			CQ.sendGroupMsg(fromGroup, ret);
		}
		return 0;
	}

	@Override
	public int discussMsg(int subtype, int msgId, long fromDiscuss, long fromQQ, String msg, int font) {
		return 0;
	}

	@Override
	public int groupUpload(int subType, int sendTime, long fromGroup, long fromQQ, String file) {
		return 0;
	}

	@Override
	public int groupAdmin(int subtype, int sendTime, long fromGroup, long beingOperateQQ) {
		return 0;
	}

	@Override
	public int groupMemberDecrease(int subtype, int sendTime, long fromGroup, long fromQQ, long beingOperateQQ) {
		return 0;
	}

	@Override
	public int groupMemberIncrease(int subtype, int sendTime, long fromGroup, long fromQQ, long beingOperateQQ) {
		return 0;
	}

	@Override
	public int friendAdd(int subtype, int sendTime, long fromQQ) {
		return 0;
	}

	@Override
	public int requestAddFriend(int subtype, int sendTime, long fromQQ, String msg, String responseFlag) {
		return 0;
	}

	@Override
	public int requestAddGroup(int subtype, int sendTime, long fromGroup, long fromQQ, String msg,
			String responseFlag) {
		return 0;
	}
	
}
