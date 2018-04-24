package joelbits.modules.analysis.plugins;

import com.google.auto.service.AutoService;
import joelbits.modules.analysis.plugins.mappers.*;
import joelbits.modules.analysis.plugins.reducers.BenchmarkMeasurementReducer;
import joelbits.modules.analysis.plugins.reducers.BenchmarkReducer;
import joelbits.modules.analysis.plugins.reducers.ConstantFoldingReducer;
import joelbits.modules.analysis.plugins.reducers.DeadCodeEliminationReducer;
import joelbits.modules.analysis.plugins.spi.Analysis;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

@AutoService(Analysis.class)
public class AnalysisPlugin implements Analysis {
    @Override
    public Class<? extends Mapper> mapper(String mapper) {
        switch (mapper) {
            case "dce":
                return DeadCodeEliminationMapper.class;
            case "cf":
                return ConstantFoldingMapper.class;
            case "configurations":
                return BenchmarkConfigurationMapper.class;
            case "count":
                return BenchmarkCountMapper.class;
            case "evolution":
                return BenchmarkEvolutionMapper.class;
            case "measurement":
                return BenchmarkMeasurementMapper.class;
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public Class<? extends Reducer> reducer(String reducer) {
        switch (reducer) {
            case "dce":
                return DeadCodeEliminationReducer.class;
            case "cf":
                return ConstantFoldingReducer.class;
            case "configurations":
            case "count":
            case "evolution":
                return BenchmarkReducer.class;
            case "measurement":
                return BenchmarkMeasurementReducer.class;
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return "jmh";
    }
}
