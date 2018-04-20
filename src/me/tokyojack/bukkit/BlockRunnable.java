package me.tokyojack.spigot.airdrop.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;

/*
 * Created by tokyojack
 * 
 * DON't REMOVE
 * 
 * GITHUB: https://github.com/tokyojack
 * DISCORD: tokyojack#7353
 * 
 */

public abstract class BlockRunnable {

	private List<Block> blocks;
	private int tickDelay; // May want to change this name

	private int runnableID;
	private JavaPlugin plugin;

	public BlockRunnable(JavaPlugin plugin) {
		this.blocks = new ArrayList<Block>();
		this.tickDelay = 20;

		this.runnableID = -1;
		this.plugin = plugin;
	}

	public BlockRunnable(int tickDelay, JavaPlugin plugin) {
		this.blocks = new ArrayList<Block>();
		this.tickDelay = tickDelay;

		this.runnableID = -1;
		this.plugin = plugin;
	}

	public BlockRunnable(JavaPlugin plugin, int tickDelay) {
		this.blocks = new ArrayList<Block>();
		this.tickDelay = tickDelay;

		this.runnableID = -1;
		this.plugin = plugin;
	}

	protected abstract void start(Block block);

	protected abstract void tick(Block block);

	protected abstract void stop(Block block);

	public void startBlock(Block block) {
		this.blocks.add(block);
		start(block);

		if (this.runnableID == -1)
			this.startPlayerRunnable();
	}

	public void stopBlock(Block block) {
		this.blocks.remove(block);
		stop(block);

		if (this.isEmpty())
			this.stopBlockRunnable();
	}

	public Boolean isEmpty() {
		return this.blocks.isEmpty();
	}

	private void startPlayerRunnable() {
		this.runnableID = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
			public void run() {
				for (Block block : blocks) {
					tick(block);
				}
			}
		}, 0, this.tickDelay);
	}

	private void stopBlockRunnable() {
		Bukkit.getScheduler().cancelTask(this.runnableID);
		this.runnableID = -1;
	}

}