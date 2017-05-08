package ii;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

import static java.nio.file.StandardWatchEventKinds.*;
/*
 * 607 Programming on Java
 * 
 * 
 */
public class Walker {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		//Получение дерева
		FileSystem fs = FileSystems.getDefault();
		Files.walkFileTree(fs.getPath("C:/Adware/"), new SimpleFileVisitor<Path>() {
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
				System.out.println("Path=" + file);
				return FileVisitResult.CONTINUE;

			}
		});
		//Отслеживание изменений в директории
		Path path = fs.getPath("C:/Adware/");
		WatchService serv = fs.newWatchService();
		path.register(serv, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
		while (true) {
			WatchKey change = serv.take();
			List<WatchEvent<?>> events = change.pollEvents();
			for (WatchEvent<?> whatchEvent : events) {
				WatchEvent<Path> pathEvent = (WatchEvent<Path>) whatchEvent;
				Path p = pathEvent.context();
				WatchEvent.Kind<Path> eventKind = pathEvent.kind();
				System.out.println(eventKind + " for Path: " + p);
			}
			change.reset();

		}

	}

}
