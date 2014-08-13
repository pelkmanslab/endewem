function drag = map(drag, verticeLabel, geneLabel)
%MAP Gene g to classification v. 
% Note that since each gene can correspond to more than one vertice 
% (class) internally mapping is defined as a function, i.e.: m: v -> g.
import endewem.*;

verticeIndex = indexOfVertice(drag, verticeLabel);
geneIndex = indexOfGene(drag, geneLabel);
m = [verticeIndex geneIndex];

% Avoid adding duplicates.
isFound = false;
[numOfRows numOfColumns] = size(drag.A);
for row = 1:numOfRows
    if isempty(find(drag.A(row, :) ~= m))
        isFound = true;
        break
    end
end

if isFound
    error('map:duplicate', ... 
          'This map has been already connected: %s', num2str(m));
end
% Append new map to annotations.
drag.A(end+1, :) = m;

end

