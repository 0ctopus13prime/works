package fakecoupangsystem.launch;

import fakespring.annotation.Meta;

import java.io.File;

/**
 * Created by buzz on 2015. 12. 26..
 */
@Meta
public class FakebuzzConfig {
	private File root;
	private boolean devMode;
	private File etcDir;

	public FakebuzzConfig() {
		root = new File("/tmp/fakecoupang");
		devMode = false;
		makeDirs();
	}

	public FakebuzzConfig(File root, File indexDir, File dataDir) {
		this.root = root;
		makeDirs();
	}

	private void makeDirs() {
		root.mkdir();
		etcDir = new File(root, "etc");
		etcDir.mkdir();
	}

	public boolean isDevMode() {
		return devMode;
	}

	public void setDevMode(boolean devMode) {
		this.devMode = devMode;
	}

	public File getRoot() {
		return root;
	}

	public void setRoot(File root) {
		this.root = root;
	}

	public File getEtcDir() {
		return etcDir;
	}

	public void setEtcDir(File etcDir) {
		this.etcDir = etcDir;
	}
}
