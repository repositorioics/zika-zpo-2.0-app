package ni.org.ics.zpo.v2.appmovil.listeners;
public interface DownloadListener {
	void downloadComplete(String result);
	void progressUpdate(String message, int progress, int max);
}
