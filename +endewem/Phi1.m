function weight = Phi1(drag, g, v)
%PHI1 Normal membership function with weighted signature (interface).
%Memebrship fucntion defined as:
%
% where
% * drag is an ontology DRAG struct with: 
% ** drag.V - all classificaitons or verticies (cell array of labels),
% ** drag.E - all dependencies or directed edges (cell array of tuples of 
%             any (v1,v2) if v1 is connected to v2 and are integers or 
%             indexes in V),
% ** drag.r - root classification,
% ** drag.G - set of all genes (cell array of labels),
% ** drag.a - is an annotation function, mapping classes in V to subsets 
%             of G (cell array of cell arrays)
%
% * g - is a gene of interest
% * v - is a vertice or a classification from drag
%
%Function returns weight \in \Z^+.

import endewem.*

geneIndex = indexOfGene(drag, g);
verticeIndex = indexOfVertice(drag, v);
m = [verticeIndex geneIndex];

isFound = false;
[numOfRows numOfColumns] = size(drag.A);
for row = 1:numOfRows
    if isempty(find(drag.A(row, :) ~= m))
        isFound = true;
        break
    end
end

weight = uint8(isFound);

end
