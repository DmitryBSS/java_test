package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by popdv on 12.09.2016.
 */
public class GithubTests {

    @Test
    public void testGithub() throws IOException {
        Github github = new RtGithub("cee28f8a7d3addb536224142af71018124c27ddb");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("DmitryBSS", "java_test")).commits();
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String,String>().build())) {
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
