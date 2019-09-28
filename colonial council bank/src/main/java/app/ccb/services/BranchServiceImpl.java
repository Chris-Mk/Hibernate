package app.ccb.services;

import app.ccb.domain.dtos.BranchImportDto;
import app.ccb.domain.entities.Branch;
import app.ccb.repositories.BranchRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final Gson gson;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository, ValidationUtil validationUtil,
                             ModelMapper modelMapper, FileUtil fileUtil, Gson gson) {
        this.branchRepository = branchRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.gson = gson;
    }

    @Override
    public Boolean branchesAreImported() {
        return this.branchRepository.count() != 0;
    }

    @Override
    public String readBranchesJsonFile() throws IOException {
        return fileUtil.readFile("files/json/branches.json");
    }

    @Override
    public String importBranches(String branchesJson) {
        StringBuilder importResult = new StringBuilder();

        final BranchImportDto[] branchImportDtos = gson.fromJson(branchesJson, BranchImportDto[].class);

        for (BranchImportDto branchImportDto : branchImportDtos) {
            if (!validationUtil.isValid(branchImportDto)) {
                importResult.append("Error: Invalid Data!");
                continue;
            }

            final Branch branch = modelMapper.map(branchImportDto, Branch.class);
            branchRepository.saveAndFlush(branch);

            importResult.append(String.format("Successfully imported Branch - %s%n", branch.getName()));
        }
        return importResult.toString().trim();
    }
}
