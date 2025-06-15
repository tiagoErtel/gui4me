package gui4me.store.dto;

import java.util.List;

public class TomTomGeocodeResponse {
    private List<Result> results;

    public List<Result> getResults() {
        return results;
    }

    public static class Result {
        private MatchConfidence matchConfidence;

        private Position position;

        public MatchConfidence getMatchConfidence() {
            return matchConfidence;
        }

        public Position getPosition() {
            return position;
        }
    }

    public static class MatchConfidence {
        private double score;

        public double getScore() {
            return score;
        }
    }

    public static class Position {
        private double lat;
        private double lon;

        public double getLat() {
            return lat;
        }

        public double getLon() {
            return lon;
        }
    }

    public Result getTheBestMatchResut() {
        if (results == null || results.isEmpty()) {
            return null;
        }

        Result bestResult = results.get(0);

        for (Result result : results) {
            if (result.getMatchConfidence() != null && bestResult.getMatchConfidence() != null) {
                if (result.getMatchConfidence().getScore() > bestResult.getMatchConfidence().getScore()) {
                    bestResult = result;
                }
            }
        }

        return bestResult;
    }
}
