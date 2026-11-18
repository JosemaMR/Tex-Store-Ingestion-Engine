package com.AadTex.Store.Ingestion.Engine.AadTex.Store.Ingestion.Engine;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.nio.file.Path;


@Component
public class StorePaths {
    private final Path base;

    public StorePaths(@Value("${aadtex.base-dir}") String baseDir) {
        this.base = projectRoot().resolve(baseDir);
    }

    // Carpeta del proyecto (la que tiene el pom.xml), calculada desde .../target/classes.
    // Así funciona aunque el IDE ejecute desde otra carpeta de trabajo.
    private static Path projectRoot() {
        try {
            Path classes = Path.of(StorePaths.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            if (classes.endsWith(Path.of("target", "classes"))) {
                return classes.getParent().getParent();
            }
        } catch (Exception e) {
            // si no se puede saber, se usa la carpeta de trabajo
        }
        return Path.of("").toAbsolutePath();
    }

    public Path keys()       { return base.resolve("keys"); }
    public Path input()      { return base.resolve("input"); }
    public Path processed()  { return base.resolve("output/processed"); }
    public Path signatures() { return base.resolve("output/processed/signatures"); }
    public Path error()      { return base.resolve("output/error"); }
    public Path bucket()     { return base.resolve("output/bucket/aadtex-daily-reports"); }
}