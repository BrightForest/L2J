package l2next.gameserver.skills.effects;

import l2next.gameserver.model.Effect;
import l2next.gameserver.stats.Env;
import l2next.gameserver.stats.Stats;

public class EffectDamOverTimeLethal extends Effect
{
	public EffectDamOverTimeLethal(Env env, EffectTemplate template)
	{
		super(env, template);
	}

	@Override
	public boolean onActionTime()
	{
		if(_effected.isDead())
		{
			return false;
		}

		double damage = calc();

		if(getSkill().isOffensive())
		{
			damage *= 2;
		}

		damage = _effector.calcStat(getSkill().isMagic() ? Stats.MAGIC_DAMAGE : Stats.PHYSICAL_DAMAGE, damage, _effected, getSkill());

		_effected.reduceCurrentHp(damage, 0, _effector, getSkill(), !_effected.isNpc() && _effected != _effector, _effected != _effector, _effector.isNpc() || _effected == _effector, false, false, true, false);

		return true;
	}
}