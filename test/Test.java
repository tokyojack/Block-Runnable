package package;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;

public class Test extends JavaPlugin {

	private BlockRunnable chestParticles = new BlockRunnable(20, this) {

		@Override
		protected void start(Block block) {

		}

		@Override
		protected void tick(Block block) {
			ParticleEffect.FIREWORKS_SPARK.display(0, 0, 0, 1, 50, block.getLocation(), 5);
		}

		@Override
		protected void stop(Block block) {
		}

	};

	private final Block particleBlock = new Location(Bukkit.getWorld("world"), 1, 2, 3).getBlock();

	public void onEnable() {
		this.chestParticles.startBlock(this.particleBlock);
	}

	public void onDisable() {
		this.chestParticles.stopBlock(this.particleBlock);
	}

}