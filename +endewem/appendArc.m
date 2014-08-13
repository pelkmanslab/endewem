function drag = appendArc(drag, label1, label2)
%ADDARC add arc
import endewem.*;

index1 = indexOfVertice(drag, label1);
index2 = indexOfVertice(drag, label2);
newArc = [index1 index2];

% Avoid adding duplicates.
isFound = false;
[numOfRows numOfColumns] = size(drag.E);
for row = 1:numOfRows
    if isempty(find(drag.E(row, :) ~= newArc))
        isFound = true;
        break
    end
end

if isFound
    error('addArc:duplicate', ... 
          'This arc has been already connected: %s', num2str(newArc));
end
% Append new arc
drag.E(end+1, :) = newArc;

end

