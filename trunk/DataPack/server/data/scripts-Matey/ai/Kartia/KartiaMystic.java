/*
 * Copyright Mazaffaka Project (c) 2013.
 */

package ai.Kartia;

import l2next.gameserver.ai.CtrlEvent;
import l2next.gameserver.ai.Mystic;
import l2next.gameserver.model.Creature;
import l2next.gameserver.model.instances.NpcInstance;

import l2next.commons.collections.GArray;

public class KartiaMystic extends Mystic {

    public KartiaMystic(NpcInstance actor) {
        super(actor);
    }

    @Override
    protected boolean thinkActive() {
        NpcInstance actor = getActor();
        if (actor.isDead())
            return false;

        GArray<NpcInstance> around = actor.getAroundNpc(3000, 3000);
        if (around != null && !around.isEmpty())
            for (NpcInstance npc : around)
                if (npc.isMonster())
                    actor.getAI().notifyEvent(CtrlEvent.EVT_AGGRESSION, npc, 300);
        return true;
    }

    @Override
    protected void onEvtAttacked(Creature attacker, int damage) {
        if (attacker == null || attacker.isPlayable())
            return;

        super.onEvtAttacked(attacker, damage);
    }

    @Override
    public int getMaxAttackTimeout() {
        return 0;
    }

    @Override
    protected boolean maybeMoveToHome() {
        return false;
    }

    @Override
    protected boolean randomWalk() {
        return false;
    }
}