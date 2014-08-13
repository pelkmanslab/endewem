function [ path ] = get_test_path()

    path = [regexprep(mfilename('fullpath'), ['\' filesep '[\w\.]*$'],'') filesep];

end
