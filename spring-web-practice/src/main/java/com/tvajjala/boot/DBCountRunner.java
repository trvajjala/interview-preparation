package com.tvajjala.boot;

import java.util.Collection;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.repository.CrudRepository;

@SuppressWarnings("rawtypes")
public class DBCountRunner implements CommandLineRunner {

    private final Collection<CrudRepository> repositories;

    public DBCountRunner(Collection<CrudRepository> repositories) {
        this.repositories = repositories;
    }

    @Override
    public void run(String... arg0) throws Exception {

        for (final CrudRepository cr : repositories) {
            System.err.println(getRepositoryName(cr.getClass()) + " has  " + cr.count() + ". <<DBCountRunner>>");
        }

    }

    static String getRepositoryName(Class repo) {
        for (final Class repoInterface : repo.getInterfaces()) {
            return repoInterface.getSimpleName();
        }
        return "UnknownRepository";
    }

}
