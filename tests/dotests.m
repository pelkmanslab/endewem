function dotests()

% Extend MATLAB PATH.
test_path = get_test_path();
path([test_path pathsep test_path(1:end-6)], path);
% Run tests.
%results = runtests(test_path(1:end-6),'Recursively',true);
results = runtests(pwd,'Recursively', true);

end