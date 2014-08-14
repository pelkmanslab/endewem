function tests = phi1Test()
    tests = functiontests(localfunctions);
end


function drag = buildExampleDrag()
import endewem.*;

drag = newDrag();
drag.V = {'r', 'v1', 'v2', 'v3', 'v4'};
drag.r = 1;
drag.E = [[1 2]; [1 5]]; % the rest connect with the function.
drag = appendArc(drag, 'v1', 'v2');
drag = appendArc(drag, 'v1', 'v4');
drag.G = {'g1', 'g2', 'g3', 'g4'};
drag = map(drag, 'v1', 'g2');
drag = map(drag, 'v2', 'g2');
drag = map(drag, 'v4', 'g2');

end

function testSmallDependency(testCase)

import endewem.*;

drag = buildExampleDrag();
drag = map(drag, 'v2', 'g3');

% v2 is connected to v2 and v4 and all are annotating g2.
expectedWeight = uint8(2); 
actualWeight = Phi2(drag, 'g2', 'v1');
testCase.verifyEqual(actualWeight, expectedWeight,...
    'Actual weight is different from expected.');

end

% function testNonExistingGeneInClass(testCase)
% 
% 
% end
