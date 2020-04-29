package fr.B4D.socket.parser;

import java.util.Arrays;

import fr.B4D.bot.B4DException;
import fr.B4D.dofus.Dofus;
import fr.B4D.interaction.chat.Channel;
import fr.B4D.interaction.chat.Message;
import fr.B4D.socket.DofusSocket;
import fr.B4D.socket.SocketElement;

public class ChatMessageSocketParser extends SocketParser<Message>{
	
	//		| Channel | Message length | Message | 0x5e | Id something | Length something | Something | Something else | Pseudo length | Pseudo | ...
	//		|    1    |        2       |    x    |   1  |       3      |         2        |     x     |        8       |        2      |    x   |
	
	public ChatMessageSocketParser() {
		super();
	}

	public Message parse(DofusSocket dofusSocket) throws B4DException {
		Channel channel = Channel.fromByte(dofusSocket.getPayload()[0]);
		
		Integer textLenght = new SocketElement(Arrays.copyOfRange(dofusSocket.getPayload(), 1, 3)).asSmallEndian();
		String text = new SocketElement(Arrays.copyOfRange(dofusSocket.getPayload(), 3, 3+textLenght)).asString();
		
		Integer lengthSomething = new SocketElement(Arrays.copyOfRange(dofusSocket.getPayload(), 3+textLenght+4, 3+textLenght+4+2)).asSmallEndian();
		
		Integer pseudoLenght = new SocketElement(Arrays.copyOfRange(dofusSocket.getPayload(), 3+textLenght+6+lengthSomething+8, 3+textLenght+6+lengthSomething+8+2)).asSmallEndian();
		String pseudo = new SocketElement(Arrays.copyOfRange(dofusSocket.getPayload(), 3+textLenght+6+lengthSomething+8+2, 3+textLenght+6+lengthSomething+8+2+pseudoLenght)).asString();
		
		Message message = new Message(pseudo, channel, text);
		Dofus.getInstance().getChat().addMessage(message);
		return message;
	}
}
