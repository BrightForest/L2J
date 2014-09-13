package l2next.gameserver.network.clientpackets;

import l2next.gameserver.model.Player;
import l2next.gameserver.model.entity.events.impl.DuelEvent;

public class RequestDuelSurrender extends L2GameClientPacket
{
	@Override
	protected void readImpl()
	{
	}

	@Override
	protected void runImpl()
	{
		Player player = getClient().getActiveChar();
		if(player == null)
		{
			return;
		}

		DuelEvent duelEvent = player.getEvent(DuelEvent.class);
		if(duelEvent == null)
		{
			return;
		}

		duelEvent.packetSurrender(player);
	}
}